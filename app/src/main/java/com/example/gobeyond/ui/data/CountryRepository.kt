package com.example.gobeyond.ui.data

import com.example.gobeyond.ui.data.local.CountryDao
import com.example.gobeyond.ui.model.Country
import kotlinx.coroutines.flow.Flow

class CountryRepository(
    private val dao: CountryDao
) {
    fun getAllCountries(): Flow<List<Country>> {
        return dao.getAllCountries()
    }

    suspend fun insertCountry(country: Country) {
        dao.insert(country)
    }

    suspend fun seedCountriesIfEmpty() {
        val current = dao.getAllCountriesOnce()
        if (current.isEmpty()) {
            dao.insert(Country("italy", "Italy"))
            dao.insert(Country("greece", "Greece"))
            dao.insert(Country("france", "France"))
        }
    }


}
