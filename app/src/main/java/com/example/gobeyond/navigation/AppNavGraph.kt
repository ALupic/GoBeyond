package com.example.gobeyond.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gobeyond.ui.explore.CountryScreen
import com.example.gobeyond.ui.explore.ExploreScreen
import com.example.gobeyond.ui.explore.ListScreen
import com.example.gobeyond.ui.explore.DestinationScreen
import com.example.gobeyond.ui.main.MainScreen

@Composable
fun AppNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Routes.MAIN
    ){
        composable(Routes.MAIN){
            MainScreen(
                onExploreClicked = {
                    navController.navigate(Routes.EXPLORE)
                },
                onLoginClicked = {
                    // TO DO
                }
            )
        }

        composable(Routes.EXPLORE){
            //ExploreScreen()
            ExploreScreen(
                onByCountryClicked = {
                    navController.navigate(Routes.LIST)
                }
            )
        }

        composable(Routes.LIST){
            //ExploreScreen()
            ListScreen{ countryId ->
                navController.navigate(Routes.country(countryId))
            }
        }

        composable(route = Routes.COUNTRY, arguments = listOf(navArgument("countryId"){
            type = NavType.StringType
        })) { backStackEntry ->
            val countryId = backStackEntry.arguments?.getString("countryId")!!

            CountryScreen(countryId = countryId, onDestinationClick = {destinationId -> navController.navigate(Routes.destination(destinationId))})
        }

        composable(route = Routes.DESTINATION, arguments = listOf(navArgument("destinationId"){
            type = NavType.StringType
        })){ backStackEntry ->
            val destinationId = backStackEntry.arguments?.getString("destinationId")!!

            DestinationScreen(destinationId = destinationId)
        }
    }
}