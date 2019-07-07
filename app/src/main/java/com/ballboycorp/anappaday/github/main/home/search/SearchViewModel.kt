package com.ballboycorp.anappaday.github.main.home.search

import androidx.appcompat.widget.SearchView
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.ballboycorp.anappaday.github.BR
import com.ballboycorp.anappaday.github.base.BaseObservableViewModel
import com.ballboycorp.anappaday.github.model.user.User
import com.ballboycorp.anappaday.github.utils.extensions.observeOnMainThread
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

/**
 * Created by musooff on 2019-07-05.
 */

class SearchViewModel : BaseObservableViewModel() {

    val searchResults: MutableLiveData<List<User>> = MutableLiveData()

    var query: String? = null
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.query)
        }

    private fun searchUsersBy(query: String) {
        addDisposable(
            githubService.users(query)
                .map { it.items }
                .flatMap {
                    Observable.zip(Observable.just(it), getFavoriteList(it).toObservable(),
                        BiFunction<List<User>,List<Int>, List<User>> { users, favoriteIds ->
                            users.map {
                                it.apply { it.isFavorite = favoriteIds.contains(it.id) }
                            }

                        })
                }
                .observeOnMainThread()
                .subscribe({
                    searchResults.value = it
                }, {
                    println(it)
                })
        )
    }

    private fun getFavoriteList(users: List<User>): Single<List<Int>> {
        return appDatabase.userDao()
            .getFavoriteId(users.map { it.id })
    }

    fun onClickSearch() {
        query
            ?.takeIf { it.isNotEmpty() }
            ?.let {
                searchUsersBy(it) }
    }

    fun onClickClear() {
        query = ""
    }
}