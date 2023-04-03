package com.example.movieapp.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun ShowMovieImages(images: List<String>){
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