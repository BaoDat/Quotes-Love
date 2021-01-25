package com.mrtdev.quoteslove.ui.allquotes.di

import androidx.lifecycle.ViewModel
import com.mrtdev.quoteslove.di.module.ViewModelKey
import com.mrtdev.quoteslove.ui.allquotes.viewmodel.AllQuotesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AllQuotesModule {

    @IntoMap
    @Binds
    @ViewModelKey(AllQuotesViewModel::class)
    abstract fun allQuotes(viewModel: AllQuotesViewModel): ViewModel

}
