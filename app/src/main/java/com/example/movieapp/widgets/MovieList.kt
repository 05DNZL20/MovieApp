package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import com.example.movieapp.models.Movie

@Composable
fun MovieRow(movie: Movie, onItemClick:(Int) -> Unit, onFavClick:(Movie) -> Unit) {
    var rotation by rememberSaveable {
        mutableStateOf(0f)
    }
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(movie.id) }
        .padding(5.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column {
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
            ) {
                AsyncImage(
                    model = movie.images[0],
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ){
                    IconButton(onClick = { onFavClick(movie) }) {
                        if (movie.isFavorite)
                        {
                            Icon(
                                tint = MaterialTheme.colors.secondary,
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Add to favorites",
                            )
                        }
                        else{
                            Icon(
                                tint = MaterialTheme.colors.secondary,
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "Add to favorites"
                            )
                        }

                    }
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(movie.title, style = MaterialTheme.typography.h6)
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Show details",
                    modifier = Modifier
                        .clickable(
                            onClickLabel = "Show details clicked",
                            onClick = {
                                expanded = !expanded
                                rotation = if (rotation == 0f) 180f else 0f
                            }
                        )
                        .rotate(rotation)
                )
            }
            AnimatedVisibility(
                visible = expanded,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Column() {
                    Text(text = "Director: ${movie.director}\n" +
                            "Released: ${movie.year}\n" +
                            "Genre: ${movie.genre}\n" +
                            "Actors: ${movie.actors}\n" +
                            "Rating: ${movie.rating}\n"
                    )
                    Divider(modifier = Modifier
                        .padding(2.dp,0.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                    Text(modifier = Modifier
                        .padding(10.dp,0.dp),
                        text = "Plot: ${movie.plot}",
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}