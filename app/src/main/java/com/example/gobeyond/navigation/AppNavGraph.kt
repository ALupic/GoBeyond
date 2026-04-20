package com.example.gobeyond.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.gobeyond.ui.data.AppDatabaseProvider
import com.example.gobeyond.ui.data.DestinationRepository
import com.example.gobeyond.ui.explore.CountryListScreen
import com.example.gobeyond.ui.explore.CountryViewModel
import com.example.gobeyond.ui.explore.DestinationScreen
import com.example.gobeyond.ui.explore.DestinationListScreen
import com.example.gobeyond.ui.explore.DestinationViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gobeyond.ui.auth.AuthState
import com.example.gobeyond.ui.auth.AuthViewModel
import com.example.gobeyond.ui.auth.LoginScreen
import com.example.gobeyond.ui.auth.ProfileScreen
import com.example.gobeyond.ui.auth.RegisterScreen
import com.example.gobeyond.ui.data.local.CountryDao
import com.example.gobeyond.ui.data.local.DestinationDao
import com.example.gobeyond.ui.explore.CategoryScreen
import com.example.gobeyond.ui.explore.ExploreScreen
import com.example.gobeyond.ui.model.Destination


@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: CountryViewModel,
    countryDao: CountryDao,
    destinationDao: DestinationDao
) {

    val authViewModel: AuthViewModel = viewModel()
    val authState = authViewModel.authState.value

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = when {
        currentRoute?.startsWith("destination/") == true -> false
        currentRoute?.startsWith("category/") == true -> false
        else -> true
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    modifier = Modifier
                        .padding(horizontal = 55.dp, vertical = 20.dp)
                        .navigationBarsPadding() // float
                        .height(56.dp)
                        .clip(RoundedCornerShape(28.dp)),
                    containerColor = MaterialTheme.colorScheme.primary,
                    tonalElevation = 6.dp
                ) {

                    NavigationBarItem(
                        selected = currentRoute == "explore",
                        onClick = {
                            navController.navigate("explore") {
                                launchSingleTop = true
                            }
                        },
                        //label = {Text("Explore", style = MaterialTheme.typography.labelMedium)},
                        icon = {
                            Box(
                                modifier = Modifier
                                    .padding(6.dp) // uniform padding on all sides
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        if (currentRoute == "explore") MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0f
                                        )
                                        else MaterialTheme.colorScheme.primary.copy(alpha = 0f) // invisible when not selected
                                    )
                                    .padding(8.dp) // inner padding for the icon inside the pill
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Explore",
                                    modifier = Modifier.size(if (currentRoute == "explore") 30.dp else 26.dp)
                                )
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                            indicatorColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0f) // subtle pill indicator
                        )
                    )

                    NavigationBarItem(
                        selected = currentRoute == "location",
                        onClick = {
                            navController.navigate("location") {
                                launchSingleTop = true
                            }
                        },
                        //label = {Text("Location", style = MaterialTheme.typography.labelMedium)},
                        icon = {
                            Box(
                                modifier = Modifier
                                    .padding(6.dp) // uniform padding on all sides
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        if (currentRoute == "explore") MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0f
                                        )
                                        else MaterialTheme.colorScheme.primary.copy(alpha = 0f) // invisible when not selected
                                    )
                                    .padding(8.dp) // inner padding for the icon inside the pill
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.LocationOn,
                                    contentDescription = "Location",
                                    modifier = Modifier.size(if (currentRoute == "location") 30.dp else 26.dp)
                                )
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                            indicatorColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0f)
                        )
                    )

                    NavigationBarItem(
                        selected = currentRoute == "countries",
                        onClick = {
                            navController.navigate("countries") {
                                popUpTo("countries") { inclusive = false }
                                launchSingleTop = true
                            }
                        },
                        //label = {Text("Countries", style = MaterialTheme.typography.labelMedium)},
                        icon = {
                            Box(
                                modifier = Modifier
                                    .padding(6.dp) // uniform padding on all sides
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        if (currentRoute == "explore") MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0f
                                        )
                                        else MaterialTheme.colorScheme.primary.copy(alpha = 0f) // invisible when not selected
                                    )
                                    .padding(8.dp) // inner padding for the icon inside the pill
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Flag,
                                    contentDescription = "Countries",
                                    modifier = Modifier.size(if (currentRoute == "countries") 30.dp else 26.dp)
                                )
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                            indicatorColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0f)
                        )
                    )

                    NavigationBarItem(
                        selected = currentRoute == "account",
                        onClick = {
                            if (authState is AuthState.Authenticated) {
                                navController.navigate("profile") {
                                    launchSingleTop = true
                                }
                            } else {
                                navController.navigate("register") {
                                    launchSingleTop = true
                                }
                            }
                        },
                        //label = {Text("Account", style = MaterialTheme.typography.labelMedium)},
                        icon = {
                            Box(
                                modifier = Modifier
                                    .padding(6.dp) // uniform padding on all sides
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        if (currentRoute == "explore") MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0f
                                        )
                                        else MaterialTheme.colorScheme.primary.copy(alpha = 0f) // invisible when not selected
                                    )
                                    .padding(8.dp) // inner padding for the icon inside the pill
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = "Account",
                                    modifier = Modifier.size(if (currentRoute == "account") 30.dp else 26.dp)
                                )
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                            indicatorColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0f)
                        )
                    )
                }
            }
        }
    ){ paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "explore",
            modifier = Modifier.fillMaxSize(),

        ) {

            composable(
                route = "category/{categoryName}",
                arguments = listOf(navArgument("categoryName") { defaultValue = "" })
            ) { backStackEntry ->
                val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
                val context = LocalContext.current
                //val db = AppDatabaseProvider.createDatabase(context)
                val repository = DestinationRepository(destinationDao, context)

                CategoryScreen(
                    category = categoryName,
                    navController = navController,
                    repository = repository
                )
            }

            composable("explore"){
                    backStackEntry ->

                //val id = backStackEntry.arguments?.getString("id") ?: ""

                val context = LocalContext.current
                //val db = AppDatabaseProvider.createDatabase(context)
                //val destinationDao = db.destinationDao()
                //val repository = DestinationRepository(db.destinationDao(), context)

                ExploreScreen(navController = navController, countryDao, destinationDao)
            }

            composable(
                route = "destinations/{countryId}/{countryName}",
                arguments = listOf(
                    navArgument("countryId") { defaultValue = "" },
                    navArgument("countryName") { defaultValue = "" }
                )
            ) { backStackEntry ->

                val countryId = backStackEntry.arguments?.getString("countryId") ?: ""
                val countryName = backStackEntry.arguments?.getString("countryName") ?: ""

                val context = LocalContext.current
                //val db = AppDatabaseProvider.createDatabase(context)
                val repository = DestinationRepository(destinationDao, context)

                val viewModel = remember(countryId) {
                    DestinationViewModel(repository, countryId)
                }

                //val destinations by viewModel.destinations.collectAsState()

                DestinationListScreen(
                    viewModel = viewModel,
                    countryName = countryName,
                    onDestinationClick = { destinationId ->
                        navController.navigate("destination/$destinationId")
                    }
                )
            }

            composable(
                route = "destination/{id}",
                arguments = listOf(navArgument("id") { defaultValue = "" })
            ) { backStackEntry ->

                val id = backStackEntry.arguments?.getString("id") ?: ""

                val context = LocalContext.current
                //val db = AppDatabaseProvider.createDatabase(context)
                val repository = DestinationRepository(destinationDao, context)

                var destination by remember { mutableStateOf<Destination?>(null) }
                var allDestinations by remember { mutableStateOf<List<Destination>>(emptyList()) }

                LaunchedEffect(id) {
                    destination = repository.getDestinationById(id)
                    allDestinations = repository.getAllDestinations() // <-- fetch all destinations
                }

                destination?.let { dest ->
                    DestinationScreen(
                        destination = dest,
                        allDestinations = allDestinations, // <-- pass full database
                        navController = navController
                    )
                }
            }

            composable("location"){
                Text("Location")
            }

            composable("countries") {
                CountryListScreen(
                    viewModel = viewModel,
                    onCountryClick = { countryId, countryName ->
                        navController.navigate("destinations/$countryId/$countryName")
                    }
                )
            }

            composable("login") {
                LoginScreen(
                    viewModel = authViewModel,
                    onNavigateToRegister = {
                        navController.navigate("register")
                    },
                    onLoginSuccess = {
                        navController.navigate("profile") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }

            composable("register") {
                RegisterScreen(
                    viewModel = authViewModel,
                    onNavigateToLogin = {
                        navController.navigate("login")
                    }
                )
            }

            composable("profile") {
                ProfileScreen(authViewModel)
            }

        }
    }

}
