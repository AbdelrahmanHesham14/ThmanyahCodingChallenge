package com.thmanyah.presentation.main

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
        startDestination = AppDestination.Home.route,
        enterTransition = {
            slideInHorizontally { it }
        },
        exitTransition = {
            slideOutHorizontally { -it }
        },
        popEnterTransition = {
            slideInHorizontally { -it }
        },
        popExitTransition = {
            slideOutHorizontally { it }
        }
    ) {
        composable(AppDestination.Home.route) {
            HomeScreen {
                navController.navigate(AppDestination.Search.route)
            }
        }
        composable(AppDestination.Search.route) {
            SearchScreen {
                navController.popBackStack()
            }
        }
    }
}