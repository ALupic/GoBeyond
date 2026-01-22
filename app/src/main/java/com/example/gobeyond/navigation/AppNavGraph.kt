package com.example.gobeyond.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gobeyond.ui.explore.ExploreScreen
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
            ExploreScreen()
        }
    }
}