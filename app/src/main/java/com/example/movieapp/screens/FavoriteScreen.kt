package com.example.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieapp.models.getMovies

@Composable
fun FavoriteScreen(navController: NavController){
    Column() {
        SimpleAppBar(navController = navController, text = "Favorites", homeOrNot = false)

        val movie = getMovies()

        MovieRow(movie = movie[0], onItemClick = { movieId ->
            navController.navigate(route = Screen.Detail.passArgument(movieId))
        })
        MovieRow(movie = movie[5], onItemClick = { movieId ->
            navController.navigate(route = Screen.Detail.passArgument(movieId))
        })
    }
}