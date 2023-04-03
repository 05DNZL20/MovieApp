package com.example.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieapp.models.MoviesViewModel
import com.example.movieapp.widgets.*

@Composable
fun FavoriteScreen(navController: NavController, viewModel: MoviesViewModel){
    Column() {
        SimpleAppBar(navController = navController, text = "Favorites", homeOrNot = false)

        FavoriteList(navController = navController, viewModel = viewModel)
    }
}
@Composable
fun FavoriteList(navController: NavController, viewModel: MoviesViewModel){
    LazyColumn() {
        items(viewModel.favoriteList()) { movie ->
            MovieRow(movie = movie,onItemClick =  { movieId ->
                navController.navigate(route = Screen.Detail.passArgument(movieId))
            }, onFavClick = { viewModel.favoriteToggler(movie)})
        }
    }
}

