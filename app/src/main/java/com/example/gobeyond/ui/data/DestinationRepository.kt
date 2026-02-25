package com.example.gobeyond.ui.data

import com.example.gobeyond.ui.data.local.DestinationDao
import com.example.gobeyond.ui.model.Country
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

    suspend fun seedDestinationsIfEmpty(countryId: String) {
        val current = dao.getAllDestinationsForCountryOnce("italy")

        if (current.isEmpty()) {
            when (countryId) {
                "italy" -> dao.insert(Destination("ventimiglia", "Ventimiglia", "italy"))
                "greece" -> dao.insert(Destination("santorini", "Santorini", "greece"))
            }
        }
    }
}