package com.ballboycorp.anappaday.github.main.home.search.utils

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ballboycorp.anappaday.github.common.PagedNetworkResult
import com.ballboycorp.anappaday.github.model.user.User
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by musooff on 2019-07-05.
 */

class SearchRepository(private val compositeDisposable: CompositeDisposable) {
    companion object {
        const val PAGE_SIZE = 25
    }

    private val config: PagedList.Config = PagedList.Config.Builder().apply {
        setInitialLoadSizeHint(PAGE_SIZE)
        setPageSize(PAGE_SIZE)
        setPrefetchDistance(5)
        setEnablePlaceholders(true)
    }.build()

    fun searchUsers(query: String): PagedNetworkResult<User> {
        val sourceFactory =
            UserDataSourceFactory(compositeDisposable, query)
        val livePagedList = LivePagedListBuilder(sourceFactory, config).build()
        return PagedNetworkResult(
            data = livePagedList,
            state = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkState })
    }
}