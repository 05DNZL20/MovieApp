package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.screens.DetailScreen
import com.example.movieapp.screens.FavoriteScreen
import com.example.movieapp.screens.HomeScreen
import com.example.movieapp.screens.Screen

@Composable
fun MyNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.Home.route) {

        composable(route = Screen.Home.route) { HomeScreen(navController = navController)}
        composable(route = Screen.Detail.route,
            arguments = listOf(navArgument("movieId") { type = NavType.StringType },
                navArgument("movieTitle") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailScreen(navController = navController,
                movieId = backStackEntry.arguments?.getString("movieId"),
                movieTitle = backStackEntry.arguments?.getString("movieTitle"))
        }
        composable(route = Screen.Favorite.route) { FavoriteScreen(navController = navController)}
    }
}
