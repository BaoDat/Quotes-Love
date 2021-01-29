package com.mrtdev.quoteslove.ui.love.di

import androidx.lifecycle.ViewModel
import com.mrtdev.quoteslove.di.module.ViewModelKey
import com.mrtdev.quoteslove.ui.love.view.FavoritesFragment
import com.mrtdev.quoteslove.ui.love.view.ListFragment
import com.mrtdev.quoteslove.ui.love.viewmodel.LoveViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class LoveInjector {

    @ContributesAndroidInjector(modules = [FavoritesModule::class])
    abstract fun likeList(): FavoritesFragment

    @ContributesAndroidInjector(modules = [ListModule::class])
    abstract fun list(): ListFragment
}

@Module
abstract class LoveModule {

    @IntoMap
    @Binds
    @ViewModelKey(LoveViewModel::class)
    abstract fun viewModel(viewModel: LoveViewModel): ViewModel
}
