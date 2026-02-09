package com.example.gobeyond.ui.data

import com.example.gobeyond.ui.data.local.DestinationDao
import com.example.gobeyond.ui.model.Destination
import kotlinx.coroutines.flow.Flow

class DestinationRepository(
    private val dao: DestinationDao
){
    fun getDestinations(countryId: String): Flow<List<Destination>>{
        return dao.getAllDestinationsForCountry(countryId)
    }

    suspend fun insert(destination: Destination){
        dao.insert(destination)
    }
}