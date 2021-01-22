package com.mrtdev.quoteslove.ui.splash.advertisement

import android.content.Context
import com.google.android.gms.ads.AdListener
import com.mrtdev.quoteslove.adcontroller.InterstitialAdController
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class QuotesLoveAdvertisement(
    context: Context,
    private val timeoutInMillis: Long,
    private val onAdvertisementLoadComplete: (successful: Boolean) -> Unit
) {

    private lateinit var disposable: Disposable
    private val  interstitialAd = InterstitialAdController.getInstance(context).interstitialAd
    private val adRequest = InterstitialAdController.getInstance(context).adRequest

    init {
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                InterstitialAdController.getInstance(context).refreshInterstitialAd()
                interstitialAd.adListener = null
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                stopTimer()
                setResult(false)
            }

            override fun onAdLoaded() {
                stopTimer()
                setResult(true)
                interstitialAd.show()
            }
        }
    }

    private fun setResult(successful: Boolean) {
        onAdvertisementLoadComplete(successful)
        interstitialAd.adListener = null
    }

    fun load() {
        interstitialAd.loadAd(adRequest)
        startTimer()
    }

    @Suppress("RedundantLambdaArrow")
    private fun startTimer() {
        disposable = Single.timer(timeoutInMillis, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _ ->
                setResult(false)
            }
    }

    private fun stopTimer() {
        if (!disposable.isDisposed)
            disposable.dispose()
    }
}