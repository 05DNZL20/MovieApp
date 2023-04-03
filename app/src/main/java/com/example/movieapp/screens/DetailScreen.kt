package com.example.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.MoviesViewModel
import com.example.movieapp.widgets.*

@Composable
fun DetailScreen(navController: NavController, movieId: String, viewModel: MoviesViewModel) {
    Column {

        SimpleAppBar(navController = navController, text = viewModel.filterMovie(movieId).title,
            homeOrNot = false)

        val movie: Movie = viewModel.filterMovie(movieId)

        MovieRow(movie = movie,onItemClick = { movieId ->
            navController.navigate(route = Screen.Detail.passArgument(movieId))
        }, onFavClick = { viewModel.favoriteToggler(movie)})

        Divider(
            modifier = Modifier
                .padding(0.dp, 10.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
        ShowMovieImages(viewModel.filterMovie(movieId).images)
    }
}

