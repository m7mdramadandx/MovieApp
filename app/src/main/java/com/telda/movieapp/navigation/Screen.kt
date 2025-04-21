package com.telda.movieapp.navigation

sealed class Screen(val route: String) {

    data object Home : Screen(route = "home")
    data object MovieDetails : Screen(route = "movieDetails")
}