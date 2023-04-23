package com.example.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.viewmodel.DetailViewModel
import com.example.movieapp.models.Movie
import com.example.movieapp.utils.InjectorUtils
import com.example.movieapp.widgets.*
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(navController: NavController, movieId: Int) {
    val viewModel: DetailViewModel = viewModel(factory = InjectorUtils.provideDetailViewModelFactory(
        LocalContext.current))

    val movie = viewModel.filterMovie(movieId)

    var coroutineScope = rememberCoroutineScope()

    Column {
        SimpleAppBar(navController = navController, text =
        movie.title,
            homeOrNot = false)



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

