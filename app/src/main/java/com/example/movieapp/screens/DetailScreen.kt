package com.example.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies

fun getDetailedMovie(movieId: String?): Movie{
    val movies = getMovies()
    var tmpMovie:Movie = movies[0]

    for (movie in movies){
        if (movie.id == movieId)
        {
            tmpMovie = movie;
        }
    }
    return tmpMovie
}

@Composable
fun DetailScreen(navController: NavController, movieId: String?, movieTitle: String?) {
    Column {
        movieTitle?.let { SimpleAppBar(navController = navController, text = it, homeOrNot = false) }

        MovieRow(movie = getDetailedMovie(movieId), onItemClick = { movieId, movieTitle ->
            navController.navigate(route = "detailscreen/${movieId}/${movieTitle}")
        })

        Divider(modifier = Modifier
            .padding(0.dp,10.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
        MovieImages(getDetailedMovie(movieId).images)
    }
}

@Composable
fun MovieImages(images: List<String>){
    Text(modifier = Modifier
        .fillMaxWidth(),
        text = "Movie Images",
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center)
    LazyRow {
        items(images) { images ->
            Card(
                modifier = Modifier
                    .padding(10.dp),
                shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                elevation = 5.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = images,
                        contentDescription = "Movie Poster",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}
