package com.example.gobeyond.ui.data

import com.example.gobeyond.ui.model.Country

object FakeCountries{
    val countries = listOf(
        Country(
            id = "italy",
            name = "Italy",
            destinations = listOf("Rome", "Florence", "Venice")
        ),
        Country(
            id = "france",
            name = "France",
            destinations = listOf("Paris", "Nice", "Strasbourg")
        ),
        Country(
            id = "spain",
            name = "Spain",
            destinations = listOf("Madrid", "Barcelona", "Seville")
        ),
    )
}