package com.mrtdev.quoteslove.ui.splash.di

import androidx.lifecycle.ViewModel
import com.mrtdev.quoteslove.di.module.ViewModelKey
import com.mrtdev.quoteslove.ui.splash.navigator.AndroidSplashScreenNavigator
import com.mrtdev.quoteslove.ui.splash.navigator.SplashScreenNavigator
import com.mrtdev.quoteslove.ui.splash.viewmodel.SplashScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SplashScreenModule {

    @Binds
    abstract fun bindNavigator(androidNavigation: AndroidSplashScreenNavigator): SplashScreenNavigator

    @IntoMap
    @Binds
    @ViewModelKey(SplashScreenViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashScreenViewModel): ViewModel
}
