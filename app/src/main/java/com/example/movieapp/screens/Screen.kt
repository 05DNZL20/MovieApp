package com.example.movieapp.screens

const val movieId = "movieID"

sealed class Screen(val route: String) {
    object Home: Screen(route = "homescreen")
    object Detail: Screen(route = "detailscreen/{$movieId}"){
        fun passArgument(movieID: Int):String{
            return "detailscreen/$movieID"
        }
    }
    object Favorite: Screen(route = "favoritescreen")

    object AddMovie: Screen(route = "addMovie")
}