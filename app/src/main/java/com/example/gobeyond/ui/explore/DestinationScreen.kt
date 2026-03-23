package com.example.gobeyond.ui.explore

import Tag
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
import coil.compose.AsyncImage
import com.google.accompanist.pager.*
import androidx.compose.material3.Divider
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DestinationScreen(destinationName: String) {

    // Hardcoded coordinates
    val (lat, lon) = when (destinationName) {
        "Matera" -> 40.6663 to 16.6043
        "Tropea" -> 38.6769 to 16.0005
        "San Vito Lo Capo" -> 38.173149 to 12.736260
        else -> 0.0 to 0.0
    }

    // Hardcoded image URLs for the carousel
    val carouselImages = listOf(
        "https://media-cdn.tripadvisor.com/media/attractions-splice-spp-674x446/0d/cf/b8/b7.jpg",
        "https://wearepalermo.com/wp-content/uploads/2023/04/san-vito-cafe.jpg",
        "https://images.squarespace-cdn.com/content/v1/6030444c2c8e1b45642b4b42/703d5e18-236d-442a-a660-1c9650d68a82/San_Vito_lo_Capo_003.jpg"
    )

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
                .height(250.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.sanvito),
                contentDescription = destinationName,
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
                text = destinationName,
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TagsSection()

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Short description
        Text(
            text = "San Vito Lo Capo is a charming coastal town in Sicily, Italy, known for its stunning white sandy beach and crystal-clear turquoise waters. Surrounded by dramatic cliffs and nature reserves, it’s perfect for sunbathing, hiking, and enjoying fresh seafood.",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Location",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "San Vito Lo Capo is a charming coastal town in Sicily, Italy, known for its stunning white sandy beach and crystal-clear turquoise waters. Surrounded by dramatic cliffs and nature reserves, it’s perfect for sunbathing, hiking, and enjoying fresh seafood.",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Map
        MapLibreMap(
            lat = lat,
            lon = lon,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Full description
        Text(
            text = "San Vito Lo Capo is a charming coastal town in Sicily, Italy, known for its stunning white sandy beach and crystal-clear turquoise waters. Surrounded by dramatic cliffs and nature reserves, it’s perfect for sunbathing, hiking, and enjoying fresh seafood. The town also hosts the famous Cous Cous Festival each year, attracting visitors from all over the world. Its narrow streets are lined with colorful buildings, quaint cafés, and artisan shops, giving it a welcoming, laid-back atmosphere.",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Image Carousel
        val pagerState = rememberPagerState()

        HorizontalPager(
            count = carouselImages.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) { page ->
            AsyncImage(
                model = carouselImages[page],
                contentDescription = "Destination image $page",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Its narrow streets are lined with colorful buildings, quaint cafés, and artisan shops, giving it a welcoming, laid-back atmosphere. Water sports like windsurfing, kayaking, and snorkeling are popular here, while the nearby Zingaro Nature Reserve offers trails with breathtaking views and hidden coves. A paradise for both relaxation and adventure.",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

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

        Spacer(modifier = Modifier.height(24.dp))

        ExploreMoreSection(destinationName)

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsSection() {

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Tag("Beach", Color(0xFFFF9800))     // Orange
        Tag("Old town", Color(0xFF795548))  // Brown
        Tag("Nature", Color(0xFF4CAF50))    // Green
        Tag("Mountain", Color(0xFF9C27B0))  // Purple
        Tag("Food", Color(0xFFF44336))      // Red
    }
}

@Composable
fun ShareButton() {
    Button(
        onClick = { /* Do nothing for now */ },
        modifier = Modifier
            .size(64.dp), // square button
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp) // slightly rounded square
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
            .fillMaxWidth()
            .height(180.dp)
            .padding(horizontal = 16.dp)
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

        Text(
            text = name,
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}

@Composable
fun ExploreMoreSection(currentDestination: String) {

    val suggestions = listOf(
        "San Vito Lo Capo" to R.drawable.sanvito,
        "Matera" to R.drawable.matera,
        "Tropea" to R.drawable.tropea
    )

    val filtered = suggestions.filter { it.first != currentDestination }

    val randomSuggestion = filtered.random()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(vertical = 24.dp)
    ) {

        Text(
            text = "Explore more destinations",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SuggestedDestinationCard(
            name = randomSuggestion.first,
            imageRes = randomSuggestion.second
        )
    }
}