package com.ballboycorp.anappaday.github.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.ballboycorp.anappaday.github.model.user.User
import io.reactivex.Single

/**
 * Created by musooff on 2019-07-07.
 */

@Dao
interface UserDao: BaseDao<User> {

    @Query("DELETE FROM User WHERE id = :id")
    fun unFavorite(id: Int): Single<Int>

    @Query("SELECT id FROM User WHERE id in (:userIds)")
    fun getFavoriteId(userIds: List<Int>): Single<List<Int>>

    @Query("SELECT * FROM User WHERE isFavorite = 1")
    fun getFavoriteUsers(): LiveData<List<User>>
}