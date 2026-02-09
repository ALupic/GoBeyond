package com.example.gobeyond.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gobeyond.ui.data.AppDatabaseProvider
import com.example.gobeyond.ui.data.DestinationRepository
import com.example.gobeyond.ui.explore.CountryListScreen
import com.example.gobeyond.ui.explore.CountryViewModel
import com.example.gobeyond.ui.explore.DestinationListScreen
import com.example.gobeyond.ui.explore.DestinationViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: CountryViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "countries"
    ) {

        composable("countries") {
            CountryListScreen(
                viewModel = viewModel,
                onCountryClick = { countryId ->
                    navController.navigate("destinations/$countryId")
                }
            )
        }

        composable("destinations/{countryId}") { backStackEntry ->
            val countryId = backStackEntry.arguments?.getString("countryId") ?: ""

            val db = AppDatabaseProvider.createDatabase(LocalContext.current)
            val repository = DestinationRepository(db.destinationDao())

            val viewModel = remember {
                DestinationViewModel(repository, countryId)
            }

            DestinationListScreen(viewModel)
        }
    }
}
