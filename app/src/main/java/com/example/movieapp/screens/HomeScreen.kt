package com.example.movieapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies


@Composable
fun HomeScreen(navController: NavController){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            SimpleAppBar(navController,"Movies",true)
            Greeting()
            Text(
                style = MaterialTheme.typography.h6,
                text= "Movie List"
            )
            MyList(navController = navController)
        }
    }
}

@Composable
fun ShowArrowBack(navController: NavController){
    Row() {
        IconButton(onClick = { navController.navigate(route = "homescreen") }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}
@Composable
fun ShowMoreVert(navController: NavController){
    var expanded by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Menu"
            )
            DropdownMenu(expanded = expanded,
                onDismissRequest = { expanded = !expanded },
            ) {
                DropdownMenuItem(onClick = { expanded = !expanded
                    navController.navigate(route = "favoritescreen")}) {
                    Icon(imageVector = Icons.Default.Favorite,
                        contentDescription = "Favourites",
                        Modifier.padding(0.dp,0.dp,5.dp,0.dp))
                    Text(text = "Favourites")
                }
            }
        }
    }
}

@Composable
fun SimpleAppBar(navController: NavController, text: String, homeOrNot: Boolean) {
    TopAppBar() {
        if(!homeOrNot)
        {
            ShowArrowBack(navController = navController)
        }

        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                style = MaterialTheme.typography.h6,
                text = text
            )
        }

        if(homeOrNot){
           ShowMoreVert(navController = navController)
        }
    }
}

@Composable
fun MyList(movies: List<Movie> = getMovies(), navController: NavController){
    LazyColumn{
        items(movies) {movie ->
            MovieRow(movie = movie) {  movieId, movieTitle ->
                navController.navigate(route = "detailscreen/${movieId}/${movieTitle}")
            }
        }
    }
}


@Composable
fun MovieRow(movie: Movie, onItemClick:(String,String) -> Unit) {
    var rotation by remember {
        mutableStateOf(0f)
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(movie.id, movie.title) }
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
            label = { Text("Name") }
        )
    }
}