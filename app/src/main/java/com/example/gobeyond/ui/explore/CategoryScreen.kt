package com.example.gobeyond.ui.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gobeyond.ui.data.DestinationRepository
import com.example.gobeyond.ui.model.Destination
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp

@Composable
fun CategoryScreen(
    category: String,
    navController: NavHostController,
    repository: DestinationRepository
) {
    var allDestinations by remember { mutableStateOf<List<Destination>>(emptyList()) }
    var currentPage by remember { mutableStateOf(0) }

    val pageSize = 10

    LaunchedEffect(category) {
        val data = repository.getAllDestinations()
        allDestinations = data.filter { dest ->
            dest.tags.split(",").map { it.trim() }.any { tag ->
                mapTagToCategory(tag) == category
            }
        }
    }

    val totalPages = (allDestinations.size + pageSize - 1) / pageSize
    val paginatedList = allDestinations
        .drop(currentPage * pageSize)
        .take(pageSize)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = category,
            style = MaterialTheme.typography.headlineLarge.copy(
                lineHeight = 36.sp // increase spacing between lines
            ),
            modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
        )

        Box(
            modifier = Modifier
                //.padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(4.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFFFD700),
                            Color(0xFFFFA000),
                            Color(0xFFFFD700)
                        )
                    )
                )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 List
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(paginatedList) { destination ->
                DestinationItem(
                    destination = destination,
                    onClick = {
                        navController.navigate("destination/${destination.id}")
                    }
                )
            }
        }

        // 🔹 Pagination controls
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.padding(16.dp),
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { currentPage-- },
                enabled = currentPage > 0
            ) {
                Text("Previous")
            }

            Text("Page ${currentPage + 1} / $totalPages")

            Button(
                onClick = { currentPage++ },
                enabled = currentPage < totalPages - 1
            ) {
                Text("Next")
            }
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
fun DestinationItem(
    destination: Destination,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(horizontal = 16.dp)
            .clickable { onClick() }
    ) {

        // 🔹 Image
        Image(
            painter = painterResource(id = destination.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Color(0xFF09072F)) // your dark navy
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 🔹 Name
        Text(
            text = destination.name,
            style = MaterialTheme.typography.titleLarge
        )

        // 🔹 Country (you can map this later properly)
        Text(
            text = destination.countryId,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

// Map tag keyword to category name
fun mapTagToCategory(tag: String): String {
    return when(tag) {
        "Beach" -> "Astonishing Beaches"
        "Mountain" -> "Mountain Hideaways"
        "Old town" -> "Timeless Towns"
        "Fairytale" -> "Fairytale Forts"
        "Ancient" -> "Ancient Wonders"
        "Food" -> "Gourmet Trails"
        "Landscape" -> "Striking Landscapes"
        "Island" -> "Island Getaways"
        "Christmas" -> "Christmas Markets"
        else -> ""
    }
}