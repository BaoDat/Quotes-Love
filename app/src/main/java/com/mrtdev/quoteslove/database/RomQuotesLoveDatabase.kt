package com.mrtdev.quoteslove.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mrtdev.quoteslove.database.dao.QuotesDao
import com.mrtdev.quoteslove.database.entity.QuotesDto
import com.mrtdev.quoteslove.database.setup.OpenHelperFactory

@Database(
    entities = [QuotesDto::class],
    version = RomQuotesLoveDatabase.DATABASE_VERSION,
    exportSchema = false
)
abstract class RomQuotesLoveDatabase : RoomDatabase() {
    companion object {
        internal const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "quotesLove"

        @JvmStatic
        @Suppress("SpreadOperator")
        fun createDatabase(context: Context): RomQuotesLoveDatabase =
            Room.databaseBuilder(
                context,
                RomQuotesLoveDatabase::class.java,
                DATABASE_NAME
            ).openHelperFactory(OpenHelperFactory())
                .build()
    }

    abstract fun quotesDao(): QuotesDao
}