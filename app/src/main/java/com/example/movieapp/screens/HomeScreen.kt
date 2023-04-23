package com.example.movieapp.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.utils.InjectorUtils
import com.example.movieapp.viewmodel.MoviesViewModel
import com.example.movieapp.widgets.*
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavController){
    val viewModel: MoviesViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(
        LocalContext.current))
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
    val coroutineScope = rememberCoroutineScope()
    val movies = viewModel.movies.collectAsState();
    LazyColumn {
        items(movies.value) { movie ->
            MovieRow(movie = movie, onItemClick = { movieId ->
                navController.navigate(route = Screen.Detail.passArgument(movieId))
            }, onFavClick = {
                coroutineScope.launch {
                    viewModel.favoriteToggler(movie)
                }
            })
        }
    }
}
