package com.mrtdev.quoteslove.ui.splash.viewmodel

import android.annotation.SuppressLint
import com.mrtdev.quoteslove.base.BaseViewModel
import com.mrtdev.quoteslove.ui.splash.navigator.SplashScreenNavigator.Event
import io.reactivex.Observable
import com.mrtdev.quoteslove.utils.plusAssign
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(

) : BaseViewModel() {

    companion object {
        private const val AD_TIMEOUT_IN_MILLIS = 10000L
    }

    private val navigationSubject = PublishSubject.create<Event>()
    val navigation: Observable<Event> = navigationSubject.hide()

    private var currentAdTimeout: Long = AD_TIMEOUT_IN_MILLIS

    @SuppressLint("CheckResult")
    fun initializeView() {
        compositeDisposable += Single.just(
            Event.ShowAdvertisement(timeout = currentAdTimeout)
        ).map {
            Result.success(it)
        }.timeout(AD_TIMEOUT_IN_MILLIS, TimeUnit.MILLISECONDS) {
            navigationSubject.onNext(Event.ShowMainActivity)
        }.observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {}
            .subscribe { result ->
                result.getOrNull()?.let { navigationSubject.onNext(it) }
            }
    }
}