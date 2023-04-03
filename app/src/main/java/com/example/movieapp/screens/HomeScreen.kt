package com.example.movieapp.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.NavController
import com.example.movieapp.models.*
import com.example.movieapp.widgets.*


@Composable
fun HomeScreen(navController: NavController, viewModel: MoviesViewModel){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            SimpleAppBar(navController,"Home",true)

            MyList(navController = navController, viewModel = viewModel)
        }
    }
}


@Composable
fun MyList(navController: NavController, viewModel: MoviesViewModel){
    LazyColumn{
        items(viewModel.movieList) {movie ->
            MovieRow(movie = movie, onItemClick =  {  movieId ->
                navController.navigate(route = Screen.Detail.passArgument(movieId))
            }, onFavClick = {viewModel.favoriteToggler(movie)})
        }
    }
}
