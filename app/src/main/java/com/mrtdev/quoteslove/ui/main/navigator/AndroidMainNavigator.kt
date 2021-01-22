package com.mrtdev.quoteslove.ui.main.navigator

import com.mrtdev.quoteslove.ui.main.view.MainActivity
import javax.inject.Inject

class AndroidMainNavigator @Inject constructor(
    private val activity: MainActivity
) : MainNavigator {

    override fun navigate(event: MainNavigator.Event): Unit {

    }

}