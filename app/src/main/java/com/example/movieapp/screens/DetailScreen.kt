package com.example.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.data.MovieDatabase
import com.example.movieapp.models.Movie
import com.example.movieapp.repositories.MovieRepository
import com.example.movieapp.viewmodel.DetailViewModel
import com.example.movieapp.viewmodel.DetailViewModelFactory
import com.example.movieapp.viewmodel.FavoriteViewModel
import com.example.movieapp.viewmodel.FavoriteViewModelFactory
import com.example.movieapp.widgets.*
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(navController: NavController, movieId: Int) {
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao())
    val factory = DetailViewModelFactory(repository = repository)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    val movie = viewModel.filterMovie(movieId)



    val coroutineScope = rememberCoroutineScope()

    Column {
        SimpleAppBar(navController = navController, text =
        movie.title, homeOrNot = false)



        MovieRow(movie = movie, onItemClick = { movieId ->
            navController.navigate(route = Screen.Detail.passArgument(movieId))
        }, onFavClick = {
            coroutineScope.launch{
                viewModel.favoriteToggler(movie)
            }
        })

        Divider(
            modifier = Modifier
                .padding(0.dp, 10.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
        ShowMovieImages(movie.images)
    }
}

