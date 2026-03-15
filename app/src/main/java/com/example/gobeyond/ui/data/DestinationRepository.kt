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
        val current = dao.getAllDestinationsForCountryOnce(countryId)

        if (current.isEmpty()) {
            val destinations = when(countryId){
                "italy" -> listOf(
                    Destination("sanvito", "San Vito Lo Capo", "italy"),
                    Destination("matera", "Matera", "italy"),
                    Destination("noto", "Noto", "italy"),
                    Destination("tropea", "Tropea", "italy"),
                    Destination("ventimiglia", "Ventimiglia", "italy"),
                    Destination("monopoli", "Monopoli", "italy")
                )
                "greece" -> listOf(
                    Destination("lindos", "Lindos", "greece"),
                    Destination("symi", "Symi", "greece"),
                    Destination("prokopios", "Agios Prokopios", "greece"),
                    Destination("metsovo", "Metsovo", "greece"),
                    Destination("olympos", "Olympos", "greece"),
                    Destination("elafonisos", "Elafonisos", "greece")
                )
                "france" -> listOf(
                    Destination("menton", "Menton", "france"),
                    Destination("carcassonne", "Carcassonne", "france"),
                    Destination("bonifacio", "Bonifacio", "france"),
                    Destination("eze", "Èze", "france"),
                    Destination("riquewihr", "Riquewihr", "france"),
                )
                "portugal" -> listOf(
                    Destination("sintra", "Sintra", "portugal"),
                    Destination("obidos", "Óbidos", "portugal"),
                    Destination("guimaraes", "Guimarães", "portugal"),
                    Destination("tavira", "Tavira", "portugal")
                )
                "spain" -> listOf(
                    Destination("capdepera", "Capdepera", "spain"),
                    Destination("deia", "Deià", "spain"),
                    Destination("masca", "Masca", "spain"),
                    Destination("elche", "Elche", "spain"),
                    Destination("juzcar", "Juzcar", "spain"),
                    Destination("morrojable", "Morro Jable", "spain"),
                )
                else -> emptyList()
            }
            destinations.forEach {dao.insert(it)}
        }
    }
}