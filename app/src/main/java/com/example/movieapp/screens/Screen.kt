package com.example.movieapp.screens

sealed class Screen(val route: String) {
    object Home: Screen(route = "homescreen")
    object Detail: Screen(route = "detailscreen/{movieId}/{movieTitle}")
    object Favorite: Screen(route = "favoritescreen")
}