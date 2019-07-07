package com.ballboycorp.anappaday.github.main.home.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ballboycorp.anappaday.github.base.BaseViewModel
import com.ballboycorp.anappaday.github.model.user.User

/**
 * Created by musooff on 2019-07-07.
 */

class FavoritesViewModel : BaseViewModel() {

    val users: LiveData<List<User>> = Transformations.map(appDatabase.userDao().getFavoriteUsers()) { it }

}