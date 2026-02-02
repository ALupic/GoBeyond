package com.example.gobeyond.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey val id: String,
    val name: String
)