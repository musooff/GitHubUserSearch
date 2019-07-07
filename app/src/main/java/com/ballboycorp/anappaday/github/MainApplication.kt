package com.ballboycorp.anappaday.github

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho

/**
 * Created by musooff on 2019-07-05.
 */
class MainApplication: Application() {

    companion object {

        private lateinit var INSTANCE: MainApplication

        fun getContext() : Context {
            return INSTANCE.applicationContext
        }

    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        Stetho.initializeWithDefaults(this)
    }
}