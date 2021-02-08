package com.mrtdev.quoteslove.database.models

data class Quote(
    val id: Long,
    val type: String,
    val description: String,
    val author: String
)