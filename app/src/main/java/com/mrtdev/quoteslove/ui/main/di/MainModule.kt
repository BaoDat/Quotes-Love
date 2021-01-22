package com.mrtdev.quoteslove.ui.main.di

import androidx.lifecycle.ViewModel
import com.mrtdev.quoteslove.di.module.ViewModelKey
import com.mrtdev.quoteslove.ui.main.navigator.AndroidMainNavigator
import com.mrtdev.quoteslove.ui.main.navigator.MainNavigator
import com.mrtdev.quoteslove.ui.main.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    abstract fun main(viewModel: MainViewModel): ViewModel

    @Binds
    abstract fun navigator(navigator: AndroidMainNavigator): MainNavigator
}

