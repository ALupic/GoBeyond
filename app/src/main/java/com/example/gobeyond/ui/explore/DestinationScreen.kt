package com.example.gobeyond.ui.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import com.example.gobeyond.ui.model.Destination
import kotlin.math.absoluteValue

//@OptIn(ExperimentalPagerApi::class)
@Composable
fun DestinationScreen(destination: Destination, allDestinations: List<Destination>) {

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

        //TagsSection(destination)

        //Spacer(modifier = Modifier.height(16.dp))

        /*Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF8F8F8)
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = destination.headText,
                modifier = Modifier.padding(16.dp)
            )
        }*/

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

        /*Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF8F8F8)
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = destination.mainText1,
                modifier = Modifier.padding(16.dp)
            )
        }*/

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


        if(beachSection){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(Icons.Default.DirectionsBike, contentDescription = null, tint = Color(0xFF09072F))
                Spacer(Modifier.width(8.dp))
                Text("Activities", style = MaterialTheme.typography.titleLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))

            /*Text(
                text = destination.mainText2,
                modifier = Modifier.padding(horizontal = 16.dp)
            )*/

            val images = listOf(
                destination.imageRes,
                destination.imageRes
            )

            val texts = listOf(
                destination.headText,
                destination.headText
            )

            val cards = images.zip(texts)
                .filter { (img, txt) ->
                    img != 0 && txt.isNotBlank()
                }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(cards) { (image, text) ->

                    InfoCard(
                        description = text,
                        imageRes = image
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
        }

        if(foodSection){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(Icons.Default.LocalPizza, contentDescription = null, tint = Color(0xFF09072F))
                Spacer(Modifier.width(8.dp))
                Text("Local Flavors", style = MaterialTheme.typography.titleLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))

            /*Text(
                text = destination.mainText2,
                modifier = Modifier.padding(horizontal = 16.dp)
            )*/

            val images = listOf(
                destination.imageRes,
                destination.imageRes,
                destination.imageRes
            )

            val texts = listOf(
                destination.headText,
                destination.headText,
                destination.headText
            )

            val cards = images.zip(texts)
                .filter { (img, txt) ->
                    img != 0 && txt.isNotBlank()
                }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(cards) { (image, text) ->

                    InfoCard(
                        description = text,
                        imageRes = image
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            //Spacer(modifier = Modifier.height(32.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))

        /*Text(
            text = "Go Beyond",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )*/

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
            text = destination.mainText2,
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
                    color = Color.LightGray,
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
                        "Download our official ${destination.name} guidebook.",
                modifier = Modifier.padding(horizontal = 16.dp) // text padding inside the gray container
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TagsSection(destination)

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),

            contentAlignment = Alignment.BottomStart
        ) {
            ShareButton()
        }

        Spacer(modifier = Modifier.height(32.dp))

        ExploreMoreSection(destination, allDestinations)

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsSection(destination: Destination) {

    val tags = destination.tags.split(",")

    val tagDisplayNames = mapOf(
        "beach" to "Beach Escapes",
        "old town" to "Timeless Towns",
        "nature" to "Nature & Outdoors",
        "mountain" to "Mountain Hideaways",
        "food" to "Gourmet Trails",
        "landscape" to "Striking Landscapes"
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
            Tag(displayName, getTagColor(tag))
        }
    }
}

fun getTagColor(tag: String): Color {
    return when (tag.trim().lowercase()) {
        "beach" -> Color(0xFF09072F)     // Orange
            //.copy(alpha = 0.15f)
        "old town" -> Color(0xFF09072F)  // Brown
        "nature" -> Color(0xFF09072F)    // Green
        "mountain" -> Color(0xFF09072F)  // Purple
        "food" -> Color(0xFF09072F)      // Red
        else -> Color.Gray
    }
}

@Composable
fun ShareButton() {
    Button(
        onClick = { /* TO DO */ },
        modifier = Modifier
            .size(64.dp), // square button
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Share"
        )
    }
}

@Composable
fun SuggestedDestinationCard(
    name: String,
    imageRes: Int
) {
    Box(
        modifier = Modifier
            .width(280.dp)
            .height(180.dp)
            //.padding(horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = name,
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
                text = name,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ExploreMoreSection(
    currentDestination: Destination,
    allDestinations: List<Destination>
) {
    // More Like This
    val currentTags = currentDestination.tags.split(",").map { it.trim().lowercase() }
    val similarVibeDestinations = allDestinations
        .filter { it.id != currentDestination.id } // exclude current
        .filter { dest -> dest.tags.split(",").any { it.trim().lowercase() in currentTags } }
        .shuffled()
        .take(2) // max 2

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
                        name = destination.name,
                        imageRes = destination.imageRes
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
                        name = destination.name,
                        imageRes = destination.imageRes
                    )
                }
            }
        }
    }
}

@Composable
fun InfoCard(
    description: String,
    imageRes: Int
) {
    Column(
        modifier = Modifier
            .width(240.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth()
        )

        Column(modifier = Modifier.padding(12.dp)) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = description, maxLines = 16)
        }
    }
}