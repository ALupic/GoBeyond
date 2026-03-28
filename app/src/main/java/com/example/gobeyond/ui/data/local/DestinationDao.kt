package com.example.gobeyond.ui.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gobeyond.ui.model.Country
import com.example.gobeyond.ui.model.Destination
import kotlinx.coroutines.flow.Flow

@Dao
interface DestinationDao {
    @Query("SELECT * FROM destinations WHERE countryId = :countryId")
    fun getAllDestinationsForCountry(countryId: String): Flow<List<Destination>>

    @Query("SELECT * FROM destinations WHERE countryId = :countryId")
    suspend fun getAllDestinationsForCountryOnce(countryId: String): List<Destination>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(destination: Destination)

    @Query("SELECT * FROM destinations WHERE id = :id LIMIT 1")
    suspend fun getDestinationById(id: String): Destination

    @Query("SELECT * FROM destinations")
    suspend fun getAllDestinations(): List<Destination>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(destinations: List<Destination>)
}