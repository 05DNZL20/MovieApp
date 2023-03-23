package com.example.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieapp.models.getMovies

@Composable
fun FavoriteScreen(navController: NavController){
    Column() {
        SimpleAppBar(navController = navController, text = "Favorites", homeOrNot = false)

        val movie = getMovies()

        MovieRow(movie = movie[0], onItemClick = { movieId, movieTitle ->
            navController.navigate(route = "detailscreen/${movieId}/${movieTitle}")
        })
        MovieRow(movie = movie[5], onItemClick = { movieId, movieTitle ->
            navController.navigate(route = "detailscreen/${movieId}/${movieTitle}")
        })
    }
}

@Composable
fun FavoriteTopBar(navController: NavController) {
    TopAppBar() {
        IconButton(onClick = {navController.navigate(route = "homescreen")}) {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back")
        }
        Text(text = "Favorites",
            style = MaterialTheme.typography.h6)
    }
}