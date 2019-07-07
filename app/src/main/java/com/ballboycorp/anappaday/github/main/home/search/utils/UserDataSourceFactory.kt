package com.ballboycorp.anappaday.github.main.home.search.utils

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.ballboycorp.anappaday.github.MainApplication
import com.ballboycorp.anappaday.github.db.AppDatabase
import com.ballboycorp.anappaday.github.model.user.User
import com.ballboycorp.anappaday.github.network.GithubService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction

/**
 * Created by musooff on 2019-07-08.
 */

class UserDataSourceFactory(private val compositeDisposable: CompositeDisposable, private val query: String) :
    DataSource.Factory<Int, User>() {
    val sourceLiveData = MutableLiveData<UserDataSource>()
    override fun create(): DataSource<Int, User> {
        val userDataSource =
            UserDataSource(compositeDisposable, query)
        sourceLiveData.postValue(userDataSource)
        return userDataSource
    }
}

class UserDataSource(private val compositeDisposable: CompositeDisposable, private val query: String) :
    PageKeyedDataSource<Int, User>() {
    val networkState = MutableLiveData<NullPointerException>()

    private val appDatabase by lazy { AppDatabase.getInstance(MainApplication.getContext()) }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {
        compositeDisposable.add(
            GithubService().users(query, 1, params.requestedLoadSize)
                .map { it.items }
                .flatMap { updateFavoriteUsers(it) }
                .subscribe({
                    callback.onResult(it, null, if (it.size == params.requestedLoadSize) 2 else null)
                }, {
                    callback.onResult(emptyList(), null, null)
                })

        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        compositeDisposable.add(
            GithubService().users(
                query,
                params.key,
                params.requestedLoadSize
            )
                .map { it.items }
                .flatMap { updateFavoriteUsers(it) }
                .subscribe({ result ->
                    callback.onResult(
                        result,
                        params.key.plus(1).takeIf { result.size >= params.requestedLoadSize })
                }, {})
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
    }

    private fun updateFavoriteUsers(remoteUsers: List<User>): Single<List<User>> {
        return Single.zip(Single.just(remoteUsers), appDatabase.userDao()
            .getFavoriteId(remoteUsers.map { it.id }),
            BiFunction { users, favoriteIds ->
                users.map {
                    it.apply {
                        isFavorite = favoriteIds.contains(it.id)
                    }
                }
            })
    }
}