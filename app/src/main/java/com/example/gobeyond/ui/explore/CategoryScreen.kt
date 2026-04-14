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
import androidx.compose.foundation.layout.width
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
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

    val listState = rememberLazyListState()

    LaunchedEffect(category) {
        val data = repository.getAllDestinations()
        allDestinations = data.filter { dest ->
            dest.tags.split(",").map { it.trim() }.any { tag ->
                mapTagToCategory(tag) == category
            }
        }
    }

    LaunchedEffect(currentPage) {
        listState.scrollToItem(0)
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

        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(paginatedList) { destination ->
                DestinationItem(
                    destination = destination,
                    onClick = {
                        navController.navigate("destination/${destination.id}")
                    },
                    callingFrom = "categoryScreen"
                )
            }
        }

        //Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                //.padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(4.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF2A2685),
                            Color(0xFF12104A),
                            Color(0xFF2A2685)
                        )
                    )
                )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Button(
                onClick = { currentPage-- },
                enabled = currentPage > 0,
                shape = RoundedCornerShape(50)
            ) {
                Text("<")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "${currentPage + 1} / $totalPages",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { currentPage++ },
                enabled = currentPage < totalPages - 1,
                shape = RoundedCornerShape(50)
            ) {
                Text(">")
            }
        }

        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun DestinationItem(
    destination: Destination,
    onClick: () -> Unit,
    callingFrom: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(horizontal = 16.dp)
            .clickable { onClick() }
    ) {

        // Image
        Image(
            painter = painterResource(id = destination.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        if(callingFrom == "categoryScreen"){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(Color(0xFF09072F))
            )
        } else { // callingFrom == "countriesScreen"
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(MaterialTheme.colorScheme.secondary)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Name
        if(callingFrom == "categoryScreen"){
            Text(
                text = destination.name,
                style = MaterialTheme.typography.titleLarge
            )
        } else { // callingFrom == "countriesScreen"
            Text(
                text = destination.name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }

        // Country
        if(callingFrom == "categoryScreen"){
            if(destination.countryId == "Bosnia"){
                Text(
                    text = "Bosnia and Herzegovina",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }else {
                Text(
                    text = destination.countryId,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        } else {
            if(destination.countryId == "Bosnia"){
                Text(
                    text = "Bosnia and Herzegovina",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }else{
                Text(
                    text = destination.countryId,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }
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