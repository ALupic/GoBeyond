package com.example.gobeyond.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destinations")
data class Destination(
    @PrimaryKey val id: String,
    val name: String,
    val countryId: String
)
