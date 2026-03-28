package com.example.gobeyond.ui.model

data class DestinationJson(
    val id: String,
    val name: String,
    val countryId: String,
    val lat: Double,
    val lon: Double,
    val imageRes: String,
    val imageUrl1: String,
    val imageUrl2: String,
    val imageUrl3: String,
    val tags: String,
    val headText: String,
    val locationText: String,
    val mainText1: String,
    val mainText2: String,
    val activitiesCarousel: String,
    val foodCarousel: String,
    val goBeyondText: String
)