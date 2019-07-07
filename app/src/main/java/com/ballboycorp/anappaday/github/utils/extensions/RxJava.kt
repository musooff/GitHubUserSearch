package com.ballboycorp.anappaday.github.utils.extensions

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by musooff on 2019-07-05.
 */

fun Completable.subscribeOnBackgroundThread() : Completable {
    return this.subscribeOn(Schedulers.io())
}

fun Completable.observeOnMainThread() : Completable {
    return this.observeOn(AndroidSchedulers.mainThread())
}

fun Completable.runOnDefaultThreads() : Completable {
    return this
            .subscribeOnBackgroundThread()
            .observeOnMainThread()
}


fun <T> Observable<T>.subscribeOnBackgroundThread() : Observable<T> {
    return this.subscribeOn(Schedulers.io())
}

fun <T> Observable<T>.observeOnMainThread() : Observable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.runOnDefaultThreads() : Observable<T> {
    return this
            .subscribeOnBackgroundThread()
            .observeOnMainThread()
}


fun <T> Single<T>.subscribeOnBackgroundThread() : Single<T> {
    return this.subscribeOn(Schedulers.io())
}

fun <T> Single<T>.observeOnMainThread() : Single<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.runOnDefaultThreads() : Single<T> {
    return this
            .subscribeOnBackgroundThread()
            .observeOnMainThread()
}