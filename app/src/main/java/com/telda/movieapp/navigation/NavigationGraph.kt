package com.geidea.billpayment.sdk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.telda.movieapp.features.home.presentation.details.MovieDetailScreen
import com.telda.movieapp.features.home.presentation.home.HomeScreen
import com.telda.movieapp.navigation.Screen
import com.telda.movieapp.utils.Constants

@Composable
fun NavigationGraph(
    navController: NavHostController,
    screenRoute: String,
) {

    NavHost(navController, startDestination = screenRoute) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToServiceDetails = {
                    navController.navigate(Screen.MovieDetails.route.plus("?$it"))
                },
            )
        }

        composable(
            Screen.MovieDetails.route.plus("?{${Constants.MOVIE_ID}}"),
            arguments = listOf(
                navArgument(Constants.MOVIE_ID) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            MovieDetailScreen()
        }


    }
}