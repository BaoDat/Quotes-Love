package com.mrtdev.quoteslove.ui.typequotes.di

import androidx.lifecycle.ViewModel
import com.mrtdev.quoteslove.di.module.ViewModelKey
import com.mrtdev.quoteslove.ui.typequotes.viewmodel.TypeQuotesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TypeQuotesModule {

    @IntoMap
    @Binds
    @ViewModelKey(TypeQuotesViewModel::class)
    abstract fun typeQuotes(viewModel: TypeQuotesViewModel): ViewModel

}
