package com.mrtdev.quoteslove.ui.main.di

import com.mrtdev.quoteslove.di.FragmentScope
import com.mrtdev.quoteslove.ui.allquotes.di.AllQuotesModule
import com.mrtdev.quoteslove.ui.allquotes.view.AllQuotesFragment
import com.mrtdev.quoteslove.ui.home.di.HomeModule
import com.mrtdev.quoteslove.ui.home.view.HomeFragment
import com.mrtdev.quoteslove.ui.info.di.InfoModule
import com.mrtdev.quoteslove.ui.info.view.InfoFragment
import com.mrtdev.quoteslove.ui.love.di.LoveInjector
import com.mrtdev.quoteslove.ui.love.di.LoveModule
import com.mrtdev.quoteslove.ui.love.view.LoveFragment
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
    @ContributesAndroidInjector(modules = [LoveModule::class, LoveInjector::class])
    abstract fun likeQuotes(): LoveFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [InfoModule::class])
    abstract fun infoQuotes(): InfoFragment
}
