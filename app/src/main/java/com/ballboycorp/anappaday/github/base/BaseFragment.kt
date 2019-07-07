package com.ballboycorp.anappaday.github.base

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by musooff on 2019-07-05.
 */

open class BaseFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
        super.onDestroyView()
    }
}