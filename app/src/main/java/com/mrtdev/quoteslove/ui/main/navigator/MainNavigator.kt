package com.mrtdev.quoteslove.ui.main.navigator

import org.parceler.Parcel

interface MainNavigator {

    @Parcel
    enum class TabItem {
        ALL,
        TYPE,
        HOME,
        LIKE,
        INFO
    }
    fun navigate(event: Event)

    sealed class Event {
    }
}