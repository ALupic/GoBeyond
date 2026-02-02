package com.example.gobeyond.ui.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gobeyond.ui.model.Country

@Database(entities = [Country::class], version = 1)
abstract class GoBeyondDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}