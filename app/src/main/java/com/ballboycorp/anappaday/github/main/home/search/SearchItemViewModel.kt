package com.ballboycorp.anappaday.github.main.home.search

import androidx.databinding.Bindable
import com.ballboycorp.anappaday.github.BR
import com.ballboycorp.anappaday.github.base.BaseObservableViewModel
import com.ballboycorp.anappaday.github.model.user.User
import com.ballboycorp.anappaday.github.utils.extensions.runOnDefaultThreads

/**
 * Created by musooff on 2019-07-07.
 */

class SearchItemViewModel(var user: User) : BaseObservableViewModel() {

    var isFavorite = user.isFavorite
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.favorite)
        }

    fun onClickLike() {
        if (!isFavorite) {
            addDisposable(
                appDatabase.userDao()
                    .insertIgnore(user.apply { isFavorite = true })
                    .runOnDefaultThreads()
                    .subscribe({
                        isFavorite = true
                    }, {
                        println(it)
                    })
            )
        } else {
            addDisposable(
                appDatabase.userDao()
                    .unFavorite(user.id)
                    .runOnDefaultThreads()
                    .subscribe({
                        isFavorite = false
                    }, {
                        println(it)
                    })
            )
        }
    }
}