package com.thmanyah.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thmanyah.presentation.home.HomeScreen
import com.thmanyah.presentation.search.SearchScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen {
                navController.navigate("search")
            }
        }
        composable("search") {
            SearchScreen {
                navController.navigateUp()
            }
        }
    }
}