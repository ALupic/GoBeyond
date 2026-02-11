package com.example.gobeyond.ui.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gobeyond.ui.model.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Query("SELECT * FROM countries")
    fun getAllCountries(): Flow<List<Country>>

    @Query("SELECT * FROM countries")
    suspend fun getAllCountriesOnce(): List<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: Country)
}
