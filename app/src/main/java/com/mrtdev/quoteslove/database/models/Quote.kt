package com.mrtdev.quoteslove.database.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class Quote @ParcelConstructor constructor(
    val id: Long,
    val type: String,
    val description: String,
    val author: String,
    var image: Int?
)