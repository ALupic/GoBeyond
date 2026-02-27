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
import com.example.gobeyond.ui.destinations.DestinationScreen
import com.example.gobeyond.ui.explore.CountryListScreen
import com.example.gobeyond.ui.explore.CountryViewModel
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
import androidx.compose.ui.unit.dp


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
                containerColor = MaterialTheme.colorScheme.surface
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

            composable("countries") {
                CountryListScreen(
                    viewModel = viewModel,
                    onCountryClick = { countryId ->
                        navController.navigate("destinations/$countryId")
                    }
                )
            }

            composable(
                route = "destinations/{countryId}",
                arguments = listOf(
                    navArgument("countryId") { defaultValue = "" }
                )
            ) { backStackEntry ->

                val countryId = backStackEntry.arguments?.getString("countryId") ?: ""

                val db = AppDatabaseProvider.createDatabase(LocalContext.current)
                val repository = DestinationRepository(db.destinationDao())

                val viewModel = remember {
                    DestinationViewModel(repository, countryId)
                }

                val destinations by viewModel.destinations.collectAsState()

                DestinationListScreen(
                    destinations = destinations,
                    onDestinationClick = { destinationName ->
                        navController.navigate("destination/$destinationName")
                    }
                )
            }

            composable(
                route = "destination/{name}",
                arguments = listOf(
                    navArgument("name") { defaultValue = "" }
                )
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                DestinationScreen(destinationName = name)
            }

            composable("location"){
                Text("Location")
            }

            composable("blog"){
                Text("Blog")
            }

            composable("account"){
                Text("Account")
            }

        }
    }

}
