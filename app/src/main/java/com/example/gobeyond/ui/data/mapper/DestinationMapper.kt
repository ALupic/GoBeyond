package com.example.gobeyond.ui.data.mapper

import android.content.Context
import com.example.gobeyond.ui.model.Destination
import com.example.gobeyond.ui.model.DestinationJson

fun DestinationJson.toDestination(context: Context): Destination {

    val imageResId = context.resources.getIdentifier(
        imageRes,
        "drawable",
        context.packageName
    )

    return Destination(
        id = id,
        name = name,
        countryId = countryId,
        lat = lat,
        lon = lon,
        imageRes = imageResId,
        imageUrl1 = imageUrl1,
        imageUrl2 = imageUrl2,
        imageUrl3 = imageUrl3,
        tags = tags,
        description = description,
        headText = headText,
        locationText = locationText,
        mainText1 = mainText1,
        mainText2 = mainText2,
        activitiesCarousel = activitiesCarousel,
        foodCarousel = foodCarousel,
        goBeyondText = goBeyondText,
        guidebook = guidebook
    )
}