package com.example.gobeyond.ui.data

import com.example.gobeyond.R
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

    suspend fun getDestinationById(id: String): Destination {
        return dao.getDestinationById(id)
    }

    suspend fun getAllDestinations(): List<Destination> {
        return dao.getAllDestinations()
    }

    suspend fun seedDestinationsIfEmpty(countryId: String) {
        val current = dao.getAllDestinationsForCountryOnce(countryId)

        if (current.isEmpty()) {
            val destinations = when(countryId){
                "italy" -> listOf(
                    Destination(
                        "sanvito",
                        "San Vito Lo Capo",
                        "italy",
                        38.173,
                        12.736,
                        R.drawable.sanvito,
                        "https://media-cdn.tripadvisor.com/media/attractions-splice-spp-674x446/0d/cf/b8/b7.jpg",
                        "https://wearepalermo.com/wp-content/uploads/2023/04/san-vito-cafe.jpg",
                        "https://images.squarespace-cdn.com/content/v1/6030444c2c8e1b45642b4b42/703d5e18-236d-442a-a660-1c9650d68a82/San_Vito_lo_Capo_003.jpg",
                        "Beach,Food,Nature",
                        "Golden sands meet turquoise dreams in San Vito Lo Capo, where Sicily reveals one of its most breathtaking seaside escapes. Between dramatic cliffs and vibrant local life, every moment feels like a perfect blend of adventure, flavor, and Mediterranean magic.",
                        "The easiest way to reach San Vito Lo Capo is by flying into nearby airports such as Palermo Airport or Trapani Airport, both about 70–100 km away. From there, you can take shuttle buses or private transfers directly to the town, or rent a car for more flexibility.\n" +
                                "\n" +
                                "There are also regular bus connections from major cities like Palermo and Trapani, with direct or connecting routes operated by regional companies. However, there is no train station in San Vito Lo Capo, so the final part of the journey is always by bus or car.",
                        "San Vito Lo Capo is a charming coastal town in Sicily, Italy, known for its stunning white sandy beach and crystal-clear turquoise waters. Surrounded by dramatic cliffs and nature reserves, it’s perfect for sunbathing, hiking, and enjoying fresh seafood. The town also hosts the famous Cous Cous Festival each year, attracting visitors from all over the world.",
                        "Its narrow streets are lined with colorful buildings, quaint cafés, and artisan shops, giving it a welcoming, laid-back atmosphere. Water sports like windsurfing, kayaking, and snorkeling are popular here, while the nearby Zingaro Nature Reserve offers trails with breathtaking views and hidden coves. A paradise for both relaxation and adventure."
                    ),
                    Destination(
                        "matera",
                        "Matera",
                        "italy",
                        38.173,
                        12.736,
                        R.drawable.matera,
                        "link1",
                        "link2",
                        "link3",
                        "Food,Nature",
                        "Beautiful town",
                        "Sicily, Italy",
                        "City famous for its sandy beaches",
                        "Nearby breathtaking views"
                    ),
                    /*Destination("noto", "Noto", "italy"),
                    Destination("tropea", "Tropea", "italy"),
                    Destination("ventimiglia", "Ventimiglia", "italy"),
                    Destination("monopoli", "Monopoli", "italy")*/
                )
                "greece" -> listOf(
                    Destination(
                        "lindos",
                        "Lindos",
                        "greece",
                        38.173,
                        12.736,
                        R.drawable.lindos,
                        "link1",
                        "link2",
                        "link3",
                        "Beach,Food,Nature",
                        "Beautiful town",
                        "Sicily, Italy",
                        "City famous for its sandy beaches",
                        "Nearby breathtaking views"
                    ),
                    /*Destination("symi", "Symi", "greece"),
                    Destination("prokopios", "Agios Prokopios", "greece"),
                    Destination("metsovo", "Metsovo", "greece"),
                    Destination("olympos", "Olympos", "greece"),
                    Destination("elafonisos", "Elafonisos", "greece")*/
                )
                /*"france" -> listOf(
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
                )*/
                else -> emptyList()
            }
            destinations.forEach {dao.insert(it)}
        }
    }
}