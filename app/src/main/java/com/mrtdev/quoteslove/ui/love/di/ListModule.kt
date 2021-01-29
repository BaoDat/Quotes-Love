package com.mrtdev.quoteslove.ui.love.di

import androidx.lifecycle.ViewModel
import com.mrtdev.quoteslove.di.module.ViewModelKey
import com.mrtdev.quoteslove.ui.love.viewmodel.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ListModule {

    @IntoMap
    @Binds
    @ViewModelKey(ListViewModel::class)
    abstract fun viewModel(viewModel: ListViewModel): ViewModel
}
