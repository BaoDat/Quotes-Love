package com.mrtdev.quoteslove.ui.splash

import android.os.Bundle
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.BaseBindingActivity
import com.mrtdev.quoteslove.databinding.SplashScreenLayoutBinding
import com.mrtdev.quoteslove.ui.splash.viewmodel.SplashScreenViewModel
import com.mrtdev.quoteslove.utils.toggleFullScreen
import com.mrtdev.quoteslove.ui.splash.navigator.SplashScreenNavigator
import javax.inject.Inject

class SplashScreenActivity : BaseBindingActivity<SplashScreenLayoutBinding, SplashScreenViewModel>() {

    override val layoutId = R.layout.splash_screen_layout
    override val viewModelClass = SplashScreenViewModel::class

    @Inject
    lateinit var navigator: SplashScreenNavigator

    override fun init(savedInstanceState: Bundle?) {
        viewModel.navigation bindTo navigator::navigate
    }

    override fun initView(savedInstanceState: Bundle?) {
        toggleFullScreen(this)
        viewModel.initializeView()
    }
}
