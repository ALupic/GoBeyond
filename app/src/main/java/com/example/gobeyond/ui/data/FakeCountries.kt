package com.example.gobeyond.ui.data

import com.example.gobeyond.R
import com.example.gobeyond.ui.model.Country

object FakeCountries{
    val countries = listOf(
        Country(
            id = "italy",
            name = "Italy",
            flagRes = R.drawable.gemsofeurope_dark
        ),
        Country(
            id = "france",
            name = "France",
            flagRes = R.drawable.gemsofeurope_dark
        ),
        Country(
            id = "spain",
            name = "Spain",
            flagRes = R.drawable.gemsofeurope_dark
        ),
    )
}