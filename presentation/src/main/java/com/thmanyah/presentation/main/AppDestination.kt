package com.thmanyah.presentation.main

sealed class AppDestination(val route: String) {
    data object Home : AppDestination("home")
    data object Search : AppDestination("search")
}