package com.example.gobeyond.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gobeyond.ui.explore.CountryListScreen
import com.example.gobeyond.ui.explore.CountryViewModel

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
            val countryId = backStackEntry.arguments?.getString("countryId")

            // TEMP placeholder checkpoint
            androidx.compose.material3.Text(
                text = "Destinations for $countryId"
            )
        }
    }
}
