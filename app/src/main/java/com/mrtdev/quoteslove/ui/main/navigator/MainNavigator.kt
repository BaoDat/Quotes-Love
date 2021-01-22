package com.mrtdev.quoteslove.ui.main.navigator

interface MainNavigator {
    fun navigate(event: Event)

    sealed class Event {
    }
}