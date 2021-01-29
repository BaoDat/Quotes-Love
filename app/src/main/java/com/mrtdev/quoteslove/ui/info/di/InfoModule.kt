package com.mrtdev.quoteslove.ui.info.di

import androidx.lifecycle.ViewModel
import com.mrtdev.quoteslove.di.module.ViewModelKey
import com.mrtdev.quoteslove.ui.info.viewmodel.InfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class InfoModule {

    @IntoMap
    @Binds
    @ViewModelKey(InfoViewModel::class)
    abstract fun viewModel(viewModel: InfoViewModel): ViewModel

}
