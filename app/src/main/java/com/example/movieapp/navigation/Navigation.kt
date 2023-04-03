package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.models.MoviesViewModel
import com.example.movieapp.screens.*

@Composable
fun MyNavigation(){
    val movieViewModel: MoviesViewModel = viewModel()

    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.Home.route) {

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, viewModel = movieViewModel)
        }
        composable(route = Screen.Detail.route,
            arguments = listOf(navArgument(movieId) { type = NavType.StringType })
        ) {
            DetailScreen(navController = navController,
                movieId = it.arguments?.getString(movieId).toString(), viewModel = movieViewModel)
        }
        composable(route = Screen.Favorite.route) {
            FavoriteScreen(navController = navController, viewModel = movieViewModel)
        }
    }
}
