package com.example.gobeyond.ui.destinations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gobeyond.ui.data.components.MapLibreMap


@Composable
fun DestinationScreen(destinationName: String) {

    val (lat, lon) = when (destinationName) {
        "Matera" -> 40.6663 to 16.6043
        "Tropea" -> 38.6769 to 16.0005
        "San Vito Lo Capo" -> 38.173149 to 12.736260
        else -> 0.0 to 0.0
    }

    Column {

        Text(
            text = destinationName,
            fontSize = 28.sp,
            modifier = Modifier.padding(24.dp)
        )

        MapLibreMap(
            lat = lat,
            lon = lon,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(horizontal = 16.dp)
        )
    }
}