package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.screens.*

@Composable
fun MyNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.Home.route) {

        composable(route = Screen.Home.route) { HomeScreen(navController = navController)}
        composable(route = Screen.Detail.route,
            arguments = listOf(navArgument(movieId) { type = NavType.StringType })
        ) {
            DetailScreen(navController = navController,
                movieId = it.arguments?.getString(movieId).toString())
        }
        composable(route = Screen.Favorite.route) { FavoriteScreen(navController = navController)}
    }
}
