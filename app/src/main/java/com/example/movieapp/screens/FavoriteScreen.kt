package com.example.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.data.MovieDatabase
import com.example.movieapp.repositories.MovieRepository
import com.example.movieapp.viewmodel.FavoriteViewModel
import com.example.movieapp.viewmodel.FavoriteViewModelFactory
import com.example.movieapp.widgets.*
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavController) {
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao())
    val factory = FavoriteViewModelFactory(repository = repository)
    val viewModel: FavoriteViewModel = viewModel(factory = factory)

    Column() {
        SimpleAppBar(navController = navController, text = "Favorites", homeOrNot = false)

        FavoriteList(navController = navController, viewModel = viewModel)
    }
}
@Composable
fun FavoriteList(navController: NavController, viewModel: FavoriteViewModel){
    val coroutineScope = rememberCoroutineScope()
    val favList by viewModel.favMovieList.collectAsState()

    LazyColumn() {
        items(items = favList) { movie ->
            MovieRow(movie = movie, onItemClick =  { movieId ->
                navController.navigate(route = Screen.Detail.passArgument(movieId))
            }, onFavClick = {
                coroutineScope.launch {
                    viewModel.favoriteToggler(movie)
                }
            })
        }
    }
}

