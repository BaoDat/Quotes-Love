package com.mrtdev.quoteslove.ui.splash.navigator

import com.mrtdev.quoteslove.ui.main.view.MainActivity
import com.mrtdev.quoteslove.ui.splash.SplashScreenActivity
import com.mrtdev.quoteslove.ui.splash.advertisement.QuotesLoveAdvertisement
import javax.inject.Inject

class AndroidSplashScreenNavigator @Inject constructor(
    private val activity: SplashScreenActivity
) : SplashScreenNavigator {
    override fun navigate(event: SplashScreenNavigator.Event) =
        when (event) {
            is SplashScreenNavigator.Event.ShowMainActivity -> showMainActivity()
            is SplashScreenNavigator.Event.ShowAdvertisement -> setupAndLoadAdvertisement(event.timeout)
        }

    private fun showMainActivity() {
        activity.run {
            startActivity(MainActivity.prepareIntent(this))
            finish()
        }
    }

    private fun setupAndLoadAdvertisement(timeout: Long) {
        QuotesLoveAdvertisement(
            activity.applicationContext,
            onAdvertisementLoadComplete = { showMainActivity() },
            timeoutInMillis = timeout
        ).load()
    }
}