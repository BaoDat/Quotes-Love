package com.mrtdev.quoteslove.ui.likequotes.di

import androidx.lifecycle.ViewModel
import com.mrtdev.quoteslove.di.module.ViewModelKey
import com.mrtdev.quoteslove.ui.likequotes.viewmodel.LikeQuotesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LikeQuotesModule {

    @IntoMap
    @Binds
    @ViewModelKey(LikeQuotesViewModel::class)
    abstract fun likeQuotes(viewModel: LikeQuotesViewModel): ViewModel

}
