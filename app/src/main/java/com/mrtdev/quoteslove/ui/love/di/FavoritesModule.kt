package com.mrtdev.quoteslove.ui.love.di

import androidx.lifecycle.ViewModel
import com.mrtdev.quoteslove.di.module.ViewModelKey
import com.mrtdev.quoteslove.ui.love.viewmodel.FavoritesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavoritesModule {

    @IntoMap
    @Binds
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun viewModel(viewModel: FavoritesViewModel): ViewModel
}