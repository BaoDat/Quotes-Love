package com.mrtdev.quoteslove.storage.di

import com.mrtdev.quoteslove.app.SessionStorage
import com.mrtdev.quoteslove.storage.SharedPreferencesStorage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    @Binds
    abstract fun provideSessionStorage(sessionStorage: SharedPreferencesStorage): SessionStorage
}