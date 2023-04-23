package com.example.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.viewmodel.FavoriteViewModel
import com.example.movieapp.utils.InjectorUtils
import com.example.movieapp.widgets.*
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavController) {
    val viewModel: FavoriteViewModel = viewModel(factory = InjectorUtils.provideFavoriteViewModelFactory(
        LocalContext.current))

    Column() {
        SimpleAppBar(navController = navController, text = "Favorites", homeOrNot = false)

        FavoriteList(navController = navController, viewModel = viewModel)
    }
}
@Composable
fun FavoriteList(navController: NavController, viewModel: FavoriteViewModel){
    val coroutineScope = rememberCoroutineScope()

    var favIconToggler by rememberSaveable {
        mutableStateOf(false)
    }

    LazyColumn() {
        items(viewModel.favMovieList.value) { movie ->
            MovieRow(movie = movie, onItemClick =  { movieId ->
                navController.navigate(route = Screen.Detail.passArgument(movieId))
            }, onFavClick = {
                coroutineScope.launch {
                    viewModel.favoriteToggler(movie)
                    favIconToggler = !favIconToggler
                }
            })
        }
    }
}

