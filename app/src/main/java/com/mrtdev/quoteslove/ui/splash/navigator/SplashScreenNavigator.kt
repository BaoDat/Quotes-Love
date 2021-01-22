package com.mrtdev.quoteslove.ui.splash.navigator

interface SplashScreenNavigator {

    fun navigate(event: Event)

    sealed class Event {
        object ShowMainActivity : Event()
        data class ShowAdvertisement(val timeout: Long) : Event()
    }
}