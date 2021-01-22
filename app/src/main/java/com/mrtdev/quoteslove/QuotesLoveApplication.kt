package com.mrtdev.quoteslove

import com.mrtdev.quoteslove.di.component.DaggerAppComponent
import com.mrtdev.quoteslove.di.component.DaggerNotificationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins


open class QuotesLoveApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler{}
    }

    override fun applicationInjector(): AndroidInjector<QuotesLoveApplication> = DaggerAppComponent.builder()
        .notification(
            DaggerNotificationComponent.builder().apply {
                context(applicationContext)
            }.build())
        .create(this)
}