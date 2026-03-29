package com.example.gobeyond.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gobeyond.ui.explore.CategoryScreen
import com.example.gobeyond.ui.explore.ExploreScreen
import com.example.gobeyond.ui.model.Destination


@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: CountryViewModel
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar(
                //modifier = Modifier.height(115.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                NavigationBarItem(
                    selected = currentRoute == "countries",
                    onClick = {
                        navController.navigate("countries"){
                            popUpTo("countries") {inclusive = false}
                            launchSingleTop = true
                        }
                    },
                    label = {Text("Explore", style = MaterialTheme.typography.labelMedium)},
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Explore"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                        indicatorColor = MaterialTheme.colorScheme.secondary
                    )
                )

                NavigationBarItem(
                    selected = currentRoute == "location",
                    onClick = {
                        navController.navigate("location"){
                            launchSingleTop = true
                        }
                    },
                    label = {Text("Location", style = MaterialTheme.typography.labelMedium)},
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "Location"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                        indicatorColor = MaterialTheme.colorScheme.secondary
                    )
                )

                NavigationBarItem(
                    selected = currentRoute == "blog",
                    onClick = {
                        navController.navigate("blog"){
                            launchSingleTop = true
                        }
                    },
                    label = {Text("Blog", style = MaterialTheme.typography.labelMedium)},
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Article,
                            contentDescription = "Blog"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                        indicatorColor = MaterialTheme.colorScheme.secondary
                    )
                )

                NavigationBarItem(
                    selected = currentRoute == "account",
                    onClick = {
                        navController.navigate("account"){
                            launchSingleTop = true
                        }
                    },
                    label = {Text("Account", style = MaterialTheme.typography.labelMedium)},
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Account"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                        indicatorColor = MaterialTheme.colorScheme.secondary
                    )
                )
            }
        }
    ){ paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "countries",
            modifier = Modifier.padding(paddingValues)
        ) {

            composable(
                route = "category/{categoryName}",
                arguments = listOf(navArgument("categoryName") { defaultValue = "" })
            ) { backStackEntry ->
                val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
                val context = LocalContext.current
                val db = AppDatabaseProvider.createDatabase(context)
                val repository = DestinationRepository(db.destinationDao(), context)

                CategoryScreen(
                    category = categoryName,
                    navController = navController,
                    repository = repository
                )
            }

            composable("countries") {
                CountryListScreen(
                    viewModel = viewModel,
                    onCountryClick = { countryId, countryName ->
                        navController.navigate("destinations/$countryId/$countryName")
                    }
                )
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
                val db = AppDatabaseProvider.createDatabase(context)
                val repository = DestinationRepository(db.destinationDao(), context)

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
                val db = AppDatabaseProvider.createDatabase(context)
                val repository = DestinationRepository(db.destinationDao(), context)

                var destination by remember { mutableStateOf<Destination?>(null) }
                var allDestinations by remember { mutableStateOf<List<Destination>>(emptyList()) }

                LaunchedEffect(id) {
                    destination = repository.getDestinationById(id)
                    allDestinations = repository.getAllDestinations() // <-- fetch all destinations
                }

                destination?.let { dest ->
                    DestinationScreen(
                        destination = dest,
                        allDestinations = allDestinations // <-- pass full database
                    )
                }
            }

            composable("location"){
                Text("Location")
            }

            composable("blog"){
                ExploreScreen(navController = navController)
            }

            composable("account"){
                Text("Account")
            }

        }
    }

}
