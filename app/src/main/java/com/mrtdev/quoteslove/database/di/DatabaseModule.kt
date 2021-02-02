package com.mrtdev.quoteslove.database.di

import android.content.Context
import com.mrtdev.quoteslove.database.RomQuotesLoveDatabase
import com.mrtdev.quoteslove.database.repositories.DatabaseQuotesDatabase
import com.mrtdev.quoteslove.database.repositories.QuotesDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DatabaseModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideQuotesLoveDatabase(context: Context): RomQuotesLoveDatabase =
            RomQuotesLoveDatabase.createDatabase(context)
    }

    @Binds
    @Singleton
    abstract fun provideQuotesStore(store: DatabaseQuotesDatabase): QuotesDatabase
}