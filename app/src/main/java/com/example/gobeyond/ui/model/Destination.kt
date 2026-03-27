package com.example.gobeyond.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destinations")
data class Destination(
    @PrimaryKey val id: String,
    val name: String,
    val countryId: String,

    val lat: Double,
    val lon: Double,
    val imageRes: Int,
    val imageUrl1: String,
    val imageUrl2: String,
    val imageUrl3: String,
    val tags: String,
    val headText: String,
    val locationText: String,
    val mainText1: String,
    val mainText2: String
)
