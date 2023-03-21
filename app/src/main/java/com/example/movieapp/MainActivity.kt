package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.models.*
import com.example.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        AppBar()
                        Greeting()
                        Text(
                            style = MaterialTheme.typography.h6,
                            text= "Movie List"
                        )
                        MyList()
                    }
                }
            }
        }
    }
}


@Composable
fun AppBar() {
    var expanded by remember {
        mutableStateOf(false)
    }
    TopAppBar() {
        Box(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                style = MaterialTheme.typography.h6,
                text = "Movies"
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu"
                )
                DropdownMenu(expanded = expanded,
                    onDismissRequest = { expanded = !expanded },
                ) {
                    DropdownMenuItem(onClick = { expanded = !expanded }) {
                        Icon(imageVector = Icons.Default.Favorite,
                            contentDescription = "Favourites",
                            Modifier.padding(0.dp,0.dp,5.dp,0.dp))
                        Text(text = "Favourites")
                    }
                }
            }
        }
    }
}

@Composable
fun MyList(movies: List<Movie> = getMovies()){
    LazyColumn{
        items(movies) {movie ->
            MovieRow(
                movie = movie
            )
        }
    }
}


@Composable
fun MovieRow(movie: Movie) {
    var rotation by remember {
        mutableStateOf(0f)
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .fillMaxWidth()
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
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites")
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
                        color = Color.Gray
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

@Composable
fun Greeting() {
    Column(modifier = Modifier
        .padding(16.dp)
    ) {
        var name by remember {
            mutableStateOf("")
        }

        Text(text = "Hello ${name}!")

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it},
            label = { Text("Name")}
        )
    }
}