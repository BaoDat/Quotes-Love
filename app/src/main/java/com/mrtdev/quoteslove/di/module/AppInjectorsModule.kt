package com.mrtdev.quoteslove.di.module

import android.content.Context
import com.mrtdev.quoteslove.QuotesLoveApplication
import com.mrtdev.quoteslove.di.ActivityScope
import com.mrtdev.quoteslove.ui.main.di.MainModule
import com.mrtdev.quoteslove.ui.main.view.MainActivity
import com.mrtdev.quoteslove.ui.splash.SplashScreenActivity
import com.mrtdev.quoteslove.ui.splash.di.SplashScreenModule

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppInjectorsModule {

    @Binds
    abstract fun context(app: QuotesLoveApplication): Context

//    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashScreenModule::class])
    abstract fun splash(): SplashScreenActivity
//
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun main(): MainActivity

}