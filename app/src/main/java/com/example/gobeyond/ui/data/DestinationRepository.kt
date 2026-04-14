package com.example.gobeyond.ui.data

import android.content.Context
import com.example.gobeyond.ui.data.local.DestinationDao
import com.example.gobeyond.ui.model.Destination
import com.example.gobeyond.ui.model.DestinationJson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import com.example.gobeyond.ui.data.mapper.toDestination

class DestinationRepository(
    private val dao: DestinationDao,
    private val context: Context
) {

    fun getDestinations(countryId: String): Flow<List<Destination>> {
        return dao.getAllDestinationsForCountry(countryId)
    }

    suspend fun insert(destination: Destination) {
        dao.insert(destination)
    }

    suspend fun getDestinationById(id: String): Destination {
        return dao.getDestinationById(id)
    }

    suspend fun getAllDestinations(): List<Destination> {
        return dao.getAllDestinations()
    }

    suspend fun getDestinationByGuidebook(guidebook: String): Destination {
        return dao.getDestinationById(guidebook)
    }

    suspend fun seedDestinationsIfEmpty() {
        val current = dao.getAllDestinations()

        if (current.isEmpty()) {
            val destinations = loadFromJson()
            destinations.forEach { dao.insert(it) }
        }
    }

    private fun loadFromJson(): List<Destination> {
        val json = context.assets.open("destinations.json")
            .bufferedReader()
            .use { it.readText() }

        val listType = object : TypeToken<List<DestinationJson>>() {}.type
        val jsonList: List<DestinationJson> = Gson().fromJson(json, listType)

        return jsonList.map { it.toDestination(context) }
    }

    suspend fun syncDestinations() {
        val destinations = loadFromJson()
        dao.insertAll(destinations)
    }
}