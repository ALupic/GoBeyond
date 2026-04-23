package com.example.gobeyond.ui.explore

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gobeyond.R
import com.example.gobeyond.ui.data.components.MapLibreMap
import com.example.gobeyond.ui.data.components.Tag
import coil.compose.AsyncImage
//import com.google.accompanist.pager.*
import androidx.compose.foundation.pager.*
import androidx.compose.material3.Divider
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.gobeyond.ui.model.Destination
import kotlin.math.absoluteValue

//@OptIn(ExperimentalPagerApi::class)
@Composable
fun DestinationScreen(
    destination: Destination,
    allDestinations: List<Destination>,
    navController: NavHostController,
    visitedPlacesViewModel: VisitedPlacesViewModel,
    savedPlacesViewModel: SavedPlacesViewModel
) {

    // Load visited and saved destinations on startup
    LaunchedEffect(Unit) {
        visitedPlacesViewModel.observeVisitedPlaces()
    }

    LaunchedEffect(Unit) {
        savedPlacesViewModel.observeSavedPlaces()
    }

    val visitedIds by visitedPlacesViewModel.visitedPlaces
    val isVisited = visitedIds.contains(destination.id)

    val savedIds by savedPlacesViewModel.savedPlaces
    val isSaved = savedIds.contains(destination.id)

    // Scrollable column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        // Top image with gradient + name overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Image(
                painter = painterResource(id = destination.imageRes),
                contentDescription = destination.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Gradient at bottom of image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.BottomEnd)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xAA000000))
                        )
                    )
            )

            // Top right options
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 40.dp, end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                // Visited circle
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        )
                        .clickable {
                            visitedPlacesViewModel.togglePlace(destination.id)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isVisited)
                            Icons.Default.Flag
                        else
                            Icons.Outlined.Flag,
                        contentDescription = "Visited",
                        tint = if (isVisited)
                            Color(0xFFFFC107)
                        else
                            Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Save circle
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        )
                        .clickable {
                            savedPlacesViewModel.togglePlace(destination.id)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isSaved)
                            Icons.Default.Bookmark
                        else
                            Icons.Outlined.BookmarkBorder,
                        contentDescription = "Save",
                        tint = if (isSaved)
                            Color(0xFFFFC107)
                        else
                            Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Destination name
            Text(
                text = destination.name,
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(7.dp)
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = destination.headText,
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(Icons.Default.Flight, contentDescription = null, tint = Color(0xFF09072F))
            Spacer(Modifier.width(8.dp))
            Text("Getting There", style = MaterialTheme.typography.titleLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = destination.locationText,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(Icons.Default.Explore, contentDescription = null, tint = Color(0xFF09072F))
            Spacer(Modifier.width(8.dp))
            Text("Explore", style = MaterialTheme.typography.titleLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Map
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            MapLibreMap(
                lat = destination.lat,
                lon = destination.lon,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    //.padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Full description
        Text(
            text = destination.mainText1,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Image Carousel
        val carouselImages = listOf(
            destination.imageUrl1,
            destination.imageUrl2,
            destination.imageUrl3
        )

        val pagerState = rememberPagerState(
            pageCount = { carouselImages.size }
        )

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 12.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) { page ->

            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                    ).absoluteValue

            val scale = 0.85f + (1 - pageOffset.coerceIn(0f, 1f)) * 0.15f

            Box(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
                    .clip(RoundedCornerShape(20.dp))
            ) {
                AsyncImage(
                    model = carouselImages[page],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // subtle gradient overlay (modern look)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.15f)
                                )
                            )
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(carouselImages.size) { index ->
                val isSelected = pagerState.currentPage == index

                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .height(6.dp)
                        .width(if (isSelected) 20.dp else 6.dp) // 👈 pill effect
                        .clip(RoundedCornerShape(50))
                        .background(
                            if (isSelected) Color.White else Color.LightGray
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = destination.mainText2,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Optional sections
        val tags = destination.tags.split(",").map { it.trim().lowercase() }

        val beachSection = "beach" in tags
        val foodSection = "food" in tags

        DestinationSlider(Icons.Default.DirectionsBike, "Activities", destination.activitiesCarousel);

        Spacer(modifier = Modifier.height(32.dp))

        if(foodSection){
            DestinationSlider(Icons.Default.LocalPizza, "Local Flavors", destination.foodCarousel);
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(Icons.Default.TravelExplore, contentDescription = null, tint = Color(0xFF09072F))
            Spacer(Modifier.width(8.dp))
            Text("Go Beyond", style = MaterialTheme.typography.titleLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = destination.goBeyondText,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp) // distance from screen edge
                .background(
                    color = Color(0xfff2eeed),
                    shape = RoundedCornerShape(16.dp) // rounded corners
                )
                .padding(vertical = 24.dp) // vertical spacing inside the container
        ) {
            Text(
                text = "To get exclusive tips and information about:\n" +
                        "• Authentic restaurants and shops\n" +
                        "• Local events and festivals\n" +
                        "• Photo spots\n" +
                        "• Ideas on creating a perfect itinerary\n" +
                        "Download our official ${destination.guidebook} guidebook.",
                modifier = Modifier.padding(horizontal = 16.dp) // text padding inside the gray container
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TagsSection(destination, navController)

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                ShareButton()

                Spacer(modifier = Modifier.width(12.dp))

                VisitedButton(
                    isVisited = isVisited,
                    onClick = {
                        visitedPlacesViewModel.togglePlace(destination.id)
                    }
                )

                Spacer(modifier = Modifier.width(12.dp))

                SaveButton(
                    isSaved = isSaved,
                    onClick = {
                        savedPlacesViewModel.togglePlace(destination.id)
                    }
                )
            }
        }


        Spacer(modifier = Modifier.height(32.dp))

        ExploreMoreSection(
            destination,
            allDestinations,
            onDestinationClick = { id ->
                navController.navigate("destination/$id")
            }
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}

//NEW
@Composable
fun DestinationSlider(icon: ImageVector, itemName: String, itemsCarousel: String){
    val context = LocalContext.current // <- get the context here

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Icon(icon, contentDescription = null, tint = Color(0xFF09072F))
        Spacer(Modifier.width(8.dp))
        Text(itemName, style = MaterialTheme.typography.titleLarge)
    }

    Spacer(modifier = Modifier.height(16.dp))

    val foodItems: List<Pair<String, String>> = itemsCarousel
        .split(";")
        .mapNotNull { item ->
            val parts = item.split("|")
            if (parts.size == 2) {
                val text = parts[0].trim()
                val imageUrl = parts[1].trim()
                text to imageUrl
            } else null
        }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(foodItems) { item ->
            val (text, imageUrl) = item
            InfoCard(
                description = text,
                imageUrl = imageUrl
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Divider(
        color = Color.LightGray,
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsSection(destination: Destination, navController: NavHostController) {

    val tags = destination.tags.split(",")

    val tagDisplayNames = mapOf(
        "beach" to "Astonishing Beaches",
        "old town" to "Timeless Towns",
        "ancient" to "Ancient Wonders",
        "fairytale" to "Fairytale Forts",
        "mountain" to "Mountain Hideaways",
        "food" to "Gourmet Trails",
        "landscape" to "Striking Landscapes",
        "island" to "Island Getaways",
        "christmas" to "Christmas Markets"
    )

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        tags.forEach { tag ->
            val displayName = tagDisplayNames[tag.trim().lowercase()] ?: tag.trim().capitalize()
            val mappedCategory = mapTagToCategory(tag.trim())

            Button(
                onClick = {
                    navController.navigate("category/$mappedCategory")
                }
            ) {
                Tag(displayName, getTagColor(tag))
            }
        }
    }
}

fun getTagColor(tag: String): Color {
    return when (tag.trim().lowercase()) {
        "beach" -> Color(0xFF09072F)     // Orange
            //.copy(alpha = 0.15f)
        "old town" -> Color(0xFF09072F)  // Brown
        "ancient" -> Color(0xFF09072F)
        "fairytale" -> Color(0xFF09072F)    // Green
        "mountain" -> Color(0xFF09072F)  // Purple
        "food" -> Color(0xFF09072F)      // Red
        "landscape" -> Color(0xFF09072F)
        "island" -> Color(0xFF09072F)
        "christmas" -> Color(0xFF09072F)
        else -> Color.Gray
    }
}

@Composable
fun ShareButton() {
    Button(
        onClick = { /* TO DO */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        contentPadding = PaddingValues(12.dp),
        modifier = Modifier.size(width = 80.dp, height = 80.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share",
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Share",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun VisitedButton(
    isVisited: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isVisited) {
        MaterialTheme.colorScheme.primary
    } else {
        Color.White
    }

    val contentColor = if (isVisited) {
        Color.White
    } else {
        MaterialTheme.colorScheme.primary
    }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        contentPadding = PaddingValues(12.dp),
        modifier = Modifier.size(width = 80.dp, height = 80.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = if (isVisited)
                    Icons.Default.Flag
                else
                    Icons.Outlined.Flag,
                contentDescription = "Visited",
                modifier = Modifier.size(32.dp),
                tint = contentColor
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Visited",
                style = MaterialTheme.typography.labelMedium,
                color = contentColor
            )
        }
    }
}

@Composable
fun SaveButton(
    isSaved: Boolean,
    onClick: () -> Unit
){
    val backgroundColor = if (isSaved) {
        MaterialTheme.colorScheme.primary
    } else {
        Color.White
    }

    val contentColor = if (isSaved) {
        Color.White
    } else {
        MaterialTheme.colorScheme.primary
    }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        contentPadding = PaddingValues(12.dp),
        modifier = Modifier.size(width = 80.dp, height = 80.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = if (isSaved)
                    Icons.Default.Bookmark
                else
                    Icons.Outlined.BookmarkBorder,
                contentDescription = "Save",
                modifier = Modifier.size(32.dp),
                tint = contentColor
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = if (isSaved) "Saved" else "Save",
                style = MaterialTheme.typography.labelMedium,
                color = contentColor
            )
        }
    }
}

@Composable
fun SuggestedDestinationCard(
    destination: Destination,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(280.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp)) // important for ripple
            .clickable { onClick() }
            //.padding(horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(id = destination.imageRes),
            contentDescription = destination.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        )

        // Gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000))
                    )
                )
        )

        // Destination name in a dark blue rounded button
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
                .background(
                    color = Color(0xFF09072F), // dark blue
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 12.dp, vertical = 6.dp) // inner padding
        ) {
            Text(
                text = destination.name,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ExploreMoreSection(
    currentDestination: Destination,
    allDestinations: List<Destination>,
    onDestinationClick: (String) -> Unit
) {
    // More Like This
    val currentTags = currentDestination.tags
        .split(",")
        .map { it.trim().lowercase() }
        .take(2)

    val similarVibeDestinations = allDestinations
        .filter { it.id != currentDestination.id }
        .filter { dest ->
            val destTags = dest.tags
                .split(",")
                .map { it.trim().lowercase() }
                .take(2)

            destTags.any { it in currentTags }
        }
        .shuffled()
        .take(2)

    // Same country
    val sameCountryDestinations = allDestinations
        .filter { it.id != currentDestination.id }
        .filter { it.countryId == currentDestination.countryId }
        .shuffled()
        .take(2)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(vertical = 24.dp)
    ) {
        // Similar vibes
        if (similarVibeDestinations.isNotEmpty()) {
            Text(
                text = "Uncover similar gems",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(similarVibeDestinations) { destination ->
                    SuggestedDestinationCard(
                        destination = destination,
                        onClick = {
                            onDestinationClick(destination.id)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Same country
        if (sameCountryDestinations.isNotEmpty()) {
            Text(
                text = "Explore more of ${currentDestination.countryId.capitalize()}",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(sameCountryDestinations) { destination ->
                    SuggestedDestinationCard(
                        destination = destination,
                        onClick = {
                            onDestinationClick(destination.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun InfoCard(
    description: String,
    imageUrl: String
) {
    Column(
        modifier = Modifier
            .width(280.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // 🔵 Attached underline (overlayed on image)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(Color(0xFF09072F))
            )
        }

        Column(modifier = Modifier.padding(2.dp)) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = description, maxLines = 25)
        }
    }
}