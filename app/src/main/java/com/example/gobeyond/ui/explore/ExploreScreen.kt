package com.example.gobeyond.ui.explore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gobeyond.ui.theme.GoBeyondTheme
import com.example.gobeyond.R
import com.example.gobeyond.ui.data.AppDatabaseProvider
import com.example.gobeyond.ui.data.CountryRepository
import com.example.gobeyond.ui.data.components.DynamicSystemNavBarColor
import com.example.gobeyond.ui.data.local.CountryDao
import com.example.gobeyond.ui.data.local.DestinationDao
import com.example.gobeyond.ui.model.Category
import com.example.gobeyond.ui.model.Country
import com.example.gobeyond.ui.model.Destination
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreScreen(navController: NavHostController, countryDao: CountryDao, destinationDao: DestinationDao){

    val scope = rememberCoroutineScope()

    val tabs = listOf("Discover", "Categories", "Countries")
    val pagerState = rememberPagerState(pageCount = { tabs.size })

    DynamicSystemNavBarColor(
        color = Color.Transparent,
        darkIcons = false
    )

    Box(modifier = Modifier.fillMaxSize()) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->

            when (page) {
                0 -> DiscoverContent(destinationDao = destinationDao, navController = navController)
                1 -> CategoriesContent(navController = navController)
                2 -> CountriesContent(navController = navController, countryDao = countryDao, destinationDao = destinationDao)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.height(40.dp)) // status bar spacing

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    modifier = Modifier.wrapContentWidth(),
                    containerColor = Color.Transparent,
                    contentColor = Color.White,
                    edgePadding = 0.dp,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            modifier = Modifier.tabIndicatorOffset(
                                tabPositions[pagerState.currentPage]
                            ),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    },
                    divider = {}
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                scope.launch { pagerState.animateScrollToPage(index) }
                            },
                            text = {
                                Text(
                                    text = title,
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DiscoverContent(destinationDao: DestinationDao, navController: NavHostController) {

    val dailyDestination = produceState<Destination?>(initialValue = null) {
        val allDestinations = destinationDao.getAllDestinations()
        value = allDestinations.getDailyItem()
    }.value

    dailyDestination?.let { dest ->
        val imageRes = dest.imageRes ?: R.drawable.noto

        Box(
            modifier = Modifier.fillMaxSize()
                .fillMaxSize()
                .clickable {
                    // Navigate to destination detail screen
                    navController.navigate("destination/${dest.id}")
                }
        ) {

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.8f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 24.dp)
                    .offset(y = (-135).dp) // shift the whole column 30.dp up
            ) {
                Text("Explore", style = MaterialTheme.typography.bodyLarge, color = Color.White)
                Text(dest.name, style = MaterialTheme.typography.headlineLarge.copy(lineHeight = 36.sp), color = Color.White)
                Text(dest.description, style = MaterialTheme.typography.bodyLarge, color = Color.White)
            }
        }
    }
}

fun <T> List<T>.getDailyItem(): T? {
    if (this.isEmpty()) return null

    val todaySeed = LocalDate.now().toEpochDay().toInt()
    val random = Random(todaySeed)
    return this[random.nextInt(this.size)]
}

@Composable
fun CategoriesContent(navController: NavHostController) {
    val categories = listOf(
        Category("Astonishing Beaches", R.drawable.cat_beach),
        Category("Mountain Hideaways", R.drawable.cat_mountain),
        Category("Timeless Towns", R.drawable.cat_oldtown),
        Category("Fairytale Forts", R.drawable.cat_fairytale),
        Category("Ancient Wonders", R.drawable.cat_ancient),
        Category("Gourmet Trails", R.drawable.cat_food),
        Category("Striking Landscapes", R.drawable.cat_landscape),
        Category("Island Getaways", R.drawable.cat_island),
        Category("Christmas Markets", R.drawable.cat_christmas)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp, bottom = 135.dp)
        ) {
            items(categories) { category ->
                CategoryItem(
                    category = category,
                    onClick = {
                        navController.navigate("category/${category.name}")
                    }
                )
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color(0xFF0D0A43))
            .clickable { onClick() }
    ) {

        // Category name (top-left)
        Text(
            text = category.name,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        )

        // Icon (bottom-right)
        Image(
            painter = painterResource(id = category.iconRes),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.BottomEnd)
                //.padding(16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesContent(
    navController: NavHostController,
    countryDao: CountryDao,
    destinationDao: DestinationDao
) {
    val context = LocalContext.current

    val countryRepository = remember { CountryRepository(countryDao) }
    val countries by countryRepository.getAllCountries()
        .collectAsState(initial = emptyList())

    var selectedCountry by remember { mutableStateOf<Country?>(null) }
    var showSheet by remember { mutableStateOf(false) }
    var destinations by remember { mutableStateOf<List<Destination>>(emptyList()) }

    // Load destinations when country changes
    LaunchedEffect(selectedCountry) {
        if(selectedCountry?.id == "Canaries"){
            selectedCountry?.let { country ->
                destinations = destinationDao.getAllDestinationsByGuidebook("Canary Islands")
                    .filter { it.guidebook == "Canary Islands" }
            }
        }else if(selectedCountry?.id == "Madeira"){
            selectedCountry?.let { country ->
                destinations = destinationDao.getAllDestinationsByGuidebook("Madeira")
                    .filter { it.guidebook == "Madeira" }
            }
        }else{
            selectedCountry?.let { country ->
                destinations = destinationDao.getAllDestinations()
                    .filter { it.countryId == country.id }
            }
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF5C54E6))
            .padding(horizontal = 16.dp, vertical = 100.dp)
    ) {

        // Modern selector button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White.copy(alpha = 0.15f))
                .clickable { showSheet = true }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = selectedCountry?.name ?: "Select a country",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(destinations) { destination ->
                DestinationItem(
                    destination = destination,
                    onClick = {
                        navController.navigate("destination/${destination.id}")
                    },
                    callingFrom = "countriesScreen"
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        // Destination list
        /*LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(destinations) { dest ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White.copy(alpha = 0.1f))
                        .clickable { navController.navigate("destination/${dest.id}") }
                        .padding(16.dp)
                ) {
                    Text(
                        text = dest.name,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }*/
    }

    // Bottom sheet for country selection
    if (showSheet) {
        CountryPickerBottomSheet(
            countries = countries,
            onSelect = {
                selectedCountry = it
                showSheet = false
            },
            onDismiss = { showSheet = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryPickerBottomSheet(
    countries: List<Country>,
    onSelect: (Country) -> Unit,
    onDismiss: () -> Unit
) {
    var search by remember { mutableStateOf("") }

    val filtered = countries.filter {
        it.name.contains(search, ignoreCase = true)
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF0D0A43),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // Drag handle
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(40.dp)
                    .height(4.dp)
                    .background(Color.Gray, RoundedCornerShape(50))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Select country",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Search bar
            TextField(
                value = search,
                onValueChange = { search = it },
                placeholder = { Text("Search...", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = 0.1f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.1f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxHeight(0.7f) // limit sheet height
            ) {
                items(filtered) { country ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White.copy(alpha = 0.08f))
                            .clickable { onSelect(country) }
                            .padding(16.dp)
                    ) {
                        Row( // Align icon and text
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = country.flagRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp) // small square icon
                                    .clip(RoundedCornerShape(4.dp))
                            )

                            Text(
                                text = country.name,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}