package com.mrtdev.quoteslove.ui.main.di

import com.mrtdev.quoteslove.di.FragmentScope
import com.mrtdev.quoteslove.ui.allquotes.di.AllQuotesModule
import com.mrtdev.quoteslove.ui.allquotes.view.AllQuotesFragment
import com.mrtdev.quoteslove.ui.home.di.HomeModule
import com.mrtdev.quoteslove.ui.home.view.HomeFragment
import com.mrtdev.quoteslove.ui.likequotes.di.LikeQuotesModule
import com.mrtdev.quoteslove.ui.likequotes.view.LikeQuotesFragment
import com.mrtdev.quoteslove.ui.typequotes.di.TypeQuotesModule
import com.mrtdev.quoteslove.ui.typequotes.view.TypeQuotesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainInjectors {

    @FragmentScope
    @ContributesAndroidInjector(modules = [AllQuotesModule::class])
    abstract fun allQuotes(): AllQuotesFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [TypeQuotesModule::class])
    abstract fun typeQuotes(): TypeQuotesFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun home(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [LikeQuotesModule::class])
    abstract fun likeQuotes(): LikeQuotesFragment
}
