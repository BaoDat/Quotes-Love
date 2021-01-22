package com.mrtdev.quoteslove.adcontroller

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.mrtdev.quoteslove.BuildConfig
import com.mrtdev.quoteslove.R

class InterstitialAdController private constructor(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: InterstitialAdController? = null

        fun getInstance(context: Context): InterstitialAdController {
            return INSTANCE
                ?: synchronized(this) {
                InterstitialAdController(
                    context
                ).also {
                    INSTANCE = it
                }
            }
        }
    }

    var interstitialAd: InterstitialAd = InterstitialAd(context)
    var adRequest: AdRequest = AdRequest.Builder().build()

    init {
        this.interstitialAd.adUnitId = if (!BuildConfig.DEBUG) {
            context.getString(R.string.interstitial_ads)
        } else {
            context.getString(R.string.interstitial_ads_test)
        }
    }

    fun refreshInterstitialAd() {
        this.adRequest = AdRequest.Builder().build()
        this.interstitialAd.loadAd(this.adRequest)
    }
}