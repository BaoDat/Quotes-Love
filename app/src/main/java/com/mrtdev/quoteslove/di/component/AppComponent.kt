package com.mrtdev.quoteslove.di.component

import com.mrtdev.quoteslove.QuotesLoveApplication
import com.mrtdev.quoteslove.di.module.AppInjectorsModule
import com.mrtdev.quoteslove.di.module.ViewModelsModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    dependencies = [NotificationComponent::class],
    modules = [
        ViewModelsModule::class,
        AndroidSupportInjectionModule::class,
        AppInjectorsModule::class
    ]
)

interface AppComponent : AndroidInjector<QuotesLoveApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<QuotesLoveApplication>() {
            abstract fun notification(notification: NotificationComponent): Builder
    }
}