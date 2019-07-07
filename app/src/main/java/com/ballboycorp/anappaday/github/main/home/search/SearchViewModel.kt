package com.ballboycorp.anappaday.github.main.home.search

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.ballboycorp.anappaday.github.BR
import com.ballboycorp.anappaday.github.base.BaseObservableViewModel
import com.ballboycorp.anappaday.github.common.PagedNetworkResult
import com.ballboycorp.anappaday.github.main.home.search.utils.SearchRepository
import com.ballboycorp.anappaday.github.model.user.User

/**
 * Created by musooff on 2019-07-05.
 */

class SearchViewModel : BaseObservableViewModel() {

    private val repository = SearchRepository(compositeDisposable)
    private val result = MutableLiveData<PagedNetworkResult<User>>()
    val pagedList: LiveData<PagedList<User>> = Transformations.switchMap(result) {it.data}

    var query: String? = null
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.query)
        }

    fun onClickSearch() {
        query
            ?.takeIf { it.isNotEmpty() }
            ?.let { result.postValue(repository.searchUsers(it)) }
    }

    fun onClickClear() {
        query = ""
    }
}