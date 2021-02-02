package com.mrtdev.quoteslove.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mrtdev.quoteslove.database.entity.QuotesDto

@Dao
interface QuotesDao {

    @Query("SELECT * FROM quotes")
    fun getQuotes(): List<QuotesDto>

    @Insert
    fun insertQuotes(quote: QuotesDto)
}