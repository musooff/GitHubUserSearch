package com.ballboycorp.anappaday.github.main.home.favorites

import com.ballboycorp.anappaday.github.base.BaseViewModel
import com.ballboycorp.anappaday.github.model.user.User
import com.ballboycorp.anappaday.github.utils.extensions.runOnDefaultThreads

/**
 * Created by musooff on 2019-07-07.
 */

class FavoriteItemViewModel(var user: User) : BaseViewModel() {

    fun onClickUnFavorite() {
        addDisposable(
            appDatabase.userDao()
                .unFavorite(user.id)
                .runOnDefaultThreads()
                .subscribe({
                }, {
                    println(it)
                })
        )
    }
}