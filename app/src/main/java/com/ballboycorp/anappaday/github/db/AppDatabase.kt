package com.ballboycorp.anappaday.github.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ballboycorp.anappaday.github.db.dao.UserDao
import com.ballboycorp.anappaday.github.model.user.User

/**
 * Created by musooff on 2019-07-05.
 */

@Database(
    entities = [
        User::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context, AppDatabase::class.java, "github.db")
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }

    }
}