package com.mrtdev.quoteslove.ui.home.di

import androidx.lifecycle.ViewModel
import com.mrtdev.quoteslove.di.module.ViewModelKey
import com.mrtdev.quoteslove.ui.home.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeModule {

    @IntoMap
    @Binds
    @ViewModelKey(HomeViewModel::class)
    abstract fun main(viewModel: HomeViewModel): ViewModel

}
