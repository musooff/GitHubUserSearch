package com.ballboycorp.anappaday.github.utils.preference

import android.content.Context

/**
 * Created by musooff on 2019-07-05.
 */

class ApplicationPreference(context: Context) {

    companion object {
        @Volatile
        private var INSTANCE: ApplicationPreference? = null

        fun getInstance(context: Context) =
                INSTANCE
                        ?: ApplicationPreference(context)
                                .also {
                                    INSTANCE = it
                                }
    }

    private val sharedPreferences = context.getSharedPreferences("TinTing", 0)
    private val editor = sharedPreferences.edit()

}