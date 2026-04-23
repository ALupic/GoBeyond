package com.example.gobeyond.ui.auth

import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.gobeyond.ui.data.DestinationRepository
import com.example.gobeyond.ui.data.local.DestinationDao
import com.example.gobeyond.ui.explore.SavedPlacesViewModel
import com.example.gobeyond.ui.explore.VisitedPlacesViewModel
import com.example.gobeyond.ui.model.Destination
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    viewModel: AuthViewModel,
    onLogout: () -> Unit,
    navController: NavHostController,
    destinationDao: DestinationDao
) {
    val authState = viewModel.authState.value
    val userEmail = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.email ?: "No email"

    val context = LocalContext.current
    val repository = remember { DestinationRepository(destinationDao, context) }

    var visitedDestinations by remember { mutableStateOf<List<Destination>>(emptyList()) }
    var savedDestinations by remember { mutableStateOf<List<Destination>>(emptyList()) }
    var allDestinations by remember { mutableStateOf<List<Destination>>(emptyList()) }

    var selectedTab by remember { mutableStateOf(0) }
    var showMenu by remember { mutableStateOf(false) }

    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(authState) {
        if (authState is AuthState.Unauthenticated) {
            onLogout()
        }
    }

    val visitedViewModel: VisitedPlacesViewModel = viewModel()
    val savedViewModel: SavedPlacesViewModel = viewModel()

    LaunchedEffect(Unit) {
        visitedViewModel.observeVisitedPlaces()
        savedViewModel.observeSavedPlaces()
        allDestinations = repository.getAllDestinations()
    }

    val visitedIds = visitedViewModel.visitedPlaces.value
    val savedIds = savedViewModel.savedPlaces.value

    LaunchedEffect(visitedIds, allDestinations) {
        visitedDestinations = allDestinations.filter { it.id in visitedIds }
    }

    LaunchedEffect(savedIds, allDestinations) {
        savedDestinations = allDestinations.filter { it.id in savedIds }
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTab = pagerState.currentPage
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // Background
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        // Menu
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(25.dp)
        ) {
            IconButton(onClick = { showMenu = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = null, tint = Color.White)
            }

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Log out") },
                    onClick = {
                        showMenu = false
                        viewModel.logout()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Edit profile") },
                    onClick = { showMenu = false }
                )
                DropdownMenuItem(
                    text = { Text("Delete account") },
                    onClick = { showMenu = false }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Avatar
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text("Photo")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = userEmail,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {

                // SEGMENTED TABS
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFF5F5F5))
                        .padding(4.dp)
                ) {

                    // VISITED
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                if (selectedTab == 0) Color(0xFF09072F) else Color.Transparent
                            )
                            .clickable {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(0)
                                }
                            }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Visited",
                            color = if (selectedTab == 0) Color.White else Color(0xFF09072F),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    // SAVED
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                if (selectedTab == 1) Color(0xFF09072F) else Color.Transparent
                            )
                            .clickable {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(1)
                                }
                            }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Saved",
                            color = if (selectedTab == 1) Color.White else Color(0xFF09072F),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }

                // SWIPEABLE CONTENT
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->

                    val list = if (page == 0) visitedDestinations else savedDestinations

                    if (list.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (page == 0)
                                    "No visited places yet"
                                else
                                    "No saved places yet",
                                color = Color.Gray
                            )
                        }
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(bottom = 100.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            items(list) { destination ->
                                ProfileDestinationCard(
                                    destination = destination,
                                    showVisitedIcon = page == 0,
                                    showSavedIcon = page == 1,
                                    onClick = {
                                        navController.navigate("destination/${destination.id}")
                                    },
                                    onIconClick = {
                                        if (page == 0) {
                                            visitedViewModel.removePlace(destination.id)
                                        } else {
                                            savedViewModel.removePlace(destination.id)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileDestinationCard(
    destination: Destination,
    showVisitedIcon: Boolean,
    showSavedIcon: Boolean,
    onClick: () -> Unit,
    onIconClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {

        Image(
            painter = painterResource(id = destination.imageRes),
            contentDescription = destination.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000))
                    )
                )
        )

        // TOP RIGHT ICON
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .size(36.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .clickable { onIconClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (showVisitedIcon) Icons.Default.Flag else Icons.Default.Bookmark,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(18.dp)
            )
        }

        // TITLE
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
                .background(Color(0xFF09072F), RoundedCornerShape(12.dp))
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(
                text = destination.name,
                color = Color.White
            )
        }
    }
}