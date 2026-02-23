package com.example.gobeyond.ui.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gobeyond.ui.model.Destination
import com.example.gobeyond.ui.theme.GoBeyondTheme

@Composable
fun DestinationListScreen(
    destinations: List<Destination>,
    onDestinationClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        destinations.forEach { destination ->
            Text(
                text = destination.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onDestinationClick(destination.name)
                    }
                    .padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DestinationListPreview() {

    val fakeDestinations = listOf(
        Destination("rome", "Rome", "italy"),
        Destination("florence", "Florence", "italy"),
        Destination("venice", "Venice", "italy")
    )

    GoBeyondTheme {
        DestinationListScreen(
            destinations = fakeDestinations,
            onDestinationClick = {}
        )
    }
}