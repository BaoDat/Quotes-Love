package com.mrtdev.quoteslove.adcontroller

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.mrtdev.quoteslove.BuildConfig
import com.mrtdev.quoteslove.R

class BannerAdController private constructor(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: BannerAdController? = null
        fun getInstance(context: Context): BannerAdController {
            return INSTANCE ?: synchronized(this){
                BannerAdController(context).also {
                    INSTANCE = it
                }
            }
        }
    }

    var bannerAd: AdView = AdView(context)
    private var adRequest: AdRequest = AdRequest.Builder().build()

    init {
        this.bannerAd.adSize = AdSize.FULL_BANNER
        this.bannerAd.adUnitId = if (!BuildConfig.DEBUG) {
            context.getString(R.string.banner_ads)
        } else {
            context.getString(R.string.banner_ads_home_footer_test)
        }
        this.bannerAd.loadAd(this.adRequest)
    }

    fun refreshBannerAd() {
        this.adRequest = AdRequest.Builder().build()
        this.bannerAd.loadAd(this.adRequest)
    }
}
