package com.example.gobeyond.ui.data

import com.example.gobeyond.R
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
            dao.insert(Country("Albania", "Albania", R.drawable.flag_al))
            dao.insert(Country("Armenia", "Armenia", R.drawable.flag_am))
            dao.insert(Country("Austria", "Austria", R.drawable.flag_at))
            dao.insert(Country("Belgium", "Belgium", R.drawable.flag_be))
            dao.insert(Country("Bosnia", "Bosnia and Herzegovina", R.drawable.flag_ba))
            dao.insert(Country("Bulgaria", "Bulgaria", R.drawable.flag_bg))
            dao.insert(Country("Canaries", "Canary Islands (Spain)", R.drawable.flag_ic))
            dao.insert(Country("Croatia", "Croatia", R.drawable.flag_hr))
            dao.insert(Country("Cyprus", "Cyprus", R.drawable.flag_cy))
            dao.insert(Country("Czechia", "Czech Republic", R.drawable.flag_cz))
            dao.insert(Country("Denmark", "Denmark", R.drawable.flag_dk))
            dao.insert(Country("Estonia", "Estonia", R.drawable.flag_ee))
            dao.insert(Country("Finland", "Finland", R.drawable.flag_fi))
            dao.insert(Country("France", "France", R.drawable.flag_fr))
            dao.insert(Country("Georgia", "Georgia", R.drawable.flag_ge))
            dao.insert(Country("Germany", "Germany", R.drawable.flag_de))
            dao.insert(Country("Greece", "Greece", R.drawable.flag_gr))
            dao.insert(Country("Hungary", "Hungary", R.drawable.flag_hu))
            dao.insert(Country("Iceland", "Iceland", R.drawable.flag_is))
            dao.insert(Country("Ireland", "Ireland", R.drawable.flag_ie))
            dao.insert(Country("Italy", "Italy", R.drawable.flag_it))
            dao.insert(Country("Latvia", "Latvia", R.drawable.flag_lv))
            dao.insert(Country("Lithuania", "Lithuania", R.drawable.flag_lt))
            dao.insert(Country("Madeira", "Madeira (Portugal)", R.drawable.flag_ptm))
            dao.insert(Country("Malta", "Malta", R.drawable.flag_mt))
            dao.insert(Country("Montenegro", "Montenegro", R.drawable.flag_me))
            dao.insert(Country("Netherlands", "Netherlands", R.drawable.flag_nl))
            dao.insert(Country("NMacedonia", "North Macedonia", R.drawable.flag_mk))
            dao.insert(Country("Norway", "Norway", R.drawable.flag_no))
            dao.insert(Country("Poland", "Poland", R.drawable.flag_pl))
            dao.insert(Country("Portugal", "Portugal", R.drawable.flag_pt))
            dao.insert(Country("Romania", "Romania", R.drawable.flag_ro))
            dao.insert(Country("Russia", "Russia", R.drawable.flag_ru))
            dao.insert(Country("Serbia", "Serbia", R.drawable.flag_rs))
            dao.insert(Country("Slovakia", "Slovakia", R.drawable.flag_sk))
            dao.insert(Country("Slovenia", "Slovenia", R.drawable.flag_si))
            dao.insert(Country("Spain", "Spain", R.drawable.flag_es))
            dao.insert(Country("Sweden", "Sweden", R.drawable.flag_se))
            dao.insert(Country("Switzerland", "Switzerland", R.drawable.flag_ch))
            dao.insert(Country("Turkey", "Turkey", R.drawable.flag_tr))
            dao.insert(Country("Ukraine", "Ukraine", R.drawable.flag_ua))
            dao.insert(Country("UK", "United Kingdom", R.drawable.flag_uk))
        }
    }


}
