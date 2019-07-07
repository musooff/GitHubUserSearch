package com.ballboycorp.anappaday.github.base

import androidx.lifecycle.ViewModel
import com.ballboycorp.anappaday.github.MainApplication
import com.ballboycorp.anappaday.github.db.AppDatabase
import com.ballboycorp.anappaday.github.network.GithubService
import com.ballboycorp.anappaday.github.utils.preference.ApplicationPreference
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by musooff on 2019-07-05.
 */

open class BaseViewModel : ViewModel() {

    val appPref by lazy { ApplicationPreference.getInstance(MainApplication.getContext()) }

    val githubService by lazy { GithubService() }

    val appDatabase by lazy { AppDatabase.getInstance(MainApplication.getContext()) }

    protected val compositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
        super.onCleared()
    }
}