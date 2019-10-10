package com.antonpopoff.testproj.presentation.common

import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView
import org.koin.core.KoinComponent
import java.util.*

open class BasePresenter<V : MvpView> : MvpPresenter<V>(), KoinComponent {

    private val disposables = Collections.synchronizedCollection(mutableListOf<Disposable>())

    override fun onDestroy() {
        super.onDestroy()
        disposables.forEach(Disposable::dispose)
    }

    fun Disposable.disposeOnDestroy() {
        disposables.add(this)
    }
}
