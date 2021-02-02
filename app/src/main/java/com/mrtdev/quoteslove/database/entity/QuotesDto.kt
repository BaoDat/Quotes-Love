package com.mrtdev.quoteslove.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuotesDto (
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "type") val type: String,
    val description: String,
    val author: String
)