package com.example.gobeyond.ui.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gobeyond.ui.model.Country
import com.example.gobeyond.ui.model.Destination

@Database(entities = [Country::class, Destination::class], version = 2)
abstract class GoBeyondDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
    abstract fun destinationDao(): DestinationDao
}