package com.example.gobeyond.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gobeyond.ui.data.FakeDestinations

@Composable
fun DestinationListScreen(
    countryId: String,
    onDestinationClick: (String) -> Unit
) {
    val destinations =
        FakeDestinations.destinationsByCountry[countryId] ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Destinations",
            modifier = Modifier.padding(8.dp)
        )

        LazyColumn {
            items(destinations) { destination ->
                Text(
                    text = destination,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onDestinationClick(destination)
                        }
                        .padding(12.dp)
                )
            }
        }
    }
}
