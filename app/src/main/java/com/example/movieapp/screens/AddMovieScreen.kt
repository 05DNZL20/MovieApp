package com.example.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.models.Genre
import com.example.movieapp.models.ListItemSelectable
import com.example.movieapp.models.Movie
import com.example.movieapp.models.MoviesViewModel
import com.example.movieapp.widgets.SimpleAppBar

@Composable
fun AddMovieScreen(navController: NavController, viewModel: MoviesViewModel){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleAppBar(navController = navController, text = "Add a Movie",
                homeOrNot = false)
            }
    ) { padding ->
        MainContent(navController, Modifier.padding(padding), viewModel)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(navController: NavController, modifier: Modifier = Modifier, viewModel: MoviesViewModel) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            var title by rememberSaveable {
                mutableStateOf("")
            }

            var year by rememberSaveable {
                mutableStateOf("")
            }

            val genres = Genre.values().toList()

            var genreItems by rememberSaveable{
                mutableStateOf(
                    genres.map { genre ->
                        ListItemSelectable(
                            title = genre.toString(),
                            isSelected = false
                        )
                    }
                )
            }

            var director by rememberSaveable {
                mutableStateOf("")
            }

            var actors by rememberSaveable {
                mutableStateOf("")
            }

            var plot by rememberSaveable {
                mutableStateOf("")
            }

            var rating by rememberSaveable {
                mutableStateOf("")
            }

            var isEnabledSaveButton by rememberSaveable {
                mutableStateOf(true)
            }

            var isTitleValid by rememberSaveable {
                mutableStateOf(false) }

            var isYearValid by rememberSaveable {
                mutableStateOf(false)
            }

            var isGenreValid by rememberSaveable {
                mutableStateOf(false)
            }

            var isDirectorValid by rememberSaveable {
                mutableStateOf(false)
            }

            var isActorValid by rememberSaveable {
                mutableStateOf(false)
            }

            var isRatingValid by rememberSaveable {
                mutableStateOf(false)
            }

            var id by rememberSaveable {
                mutableStateOf(0)
            }

            Column() {
                OutlinedTextField(
                    value = title,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
                    onValueChange = {
                        title = it
                        isTitleValid = viewModel.checkValidation(it,false)
                    },
                    label = {
                        Text(text = "Enter title")
                    },
                    isError = !isTitleValid
                )

                if (!isTitleValid) {
                    Text(text = "Title is required!", color = MaterialTheme.colors.error)
                }
            }

            Column() {
                OutlinedTextField(
                    value = year,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        year = it
                        isYearValid = viewModel.checkValidation(it,false)
                    },
                    label = { Text(text = "Enter year") },
                    isError = !isYearValid
                )
                if (!isYearValid) {
                    Text(text = "Year is required!", color = MaterialTheme.colors.error)
                }
            }


            Column() {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "Select Genres",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h6
                )

                LazyHorizontalGrid(
                    modifier = Modifier.height(100.dp),
                    rows = GridCells.Fixed(3)
                ) {
                    items(genreItems) { genreItem ->
                        Chip(
                            modifier = Modifier.padding(2.dp),
                            colors = ChipDefaults.chipColors(
                                backgroundColor = if (genreItem.isSelected)
                                    colorResource(id = R.color.purple_200)
                                else
                                    colorResource(id = R.color.white)
                            ),
                            onClick = {
                                genreItems = genreItems.map {
                                    if (it.title == genreItem.title) {
                                        genreItem.copy(isSelected = !genreItem.isSelected)
                                    } else {
                                        it
                                    }
                                }
                                isGenreValid = genreItems.any { it.isSelected }
                            }
                        ) {
                            Text(text = genreItem.title)
                        }
                    }
                }

                if (!isGenreValid) {
                    Text(text = "Genre is required!", color = MaterialTheme.colors.error)
                }
            }

            Column() {
                OutlinedTextField(
                    value = director,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        director = it
                        isDirectorValid = viewModel.checkValidation(it,false) },
                    label = { Text(stringResource(R.string.enter_director)) },
                    isError = !isDirectorValid
                )
                if (!isDirectorValid) {
                    Text(text = "Director is required!", color = MaterialTheme.colors.error)
                }
            }

            Column() {
                OutlinedTextField(
                    value = actors,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        actors = it
                        isActorValid = viewModel.checkValidation(it,false)},
                    label = { Text(stringResource(R.string.enter_actors)) },
                    isError = !isActorValid
                )
                if (!isActorValid) {
                    Text(text = "Actor is required!", color = MaterialTheme.colors.error)
                }
            }

            Column() {
                OutlinedTextField(
                    value = plot,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    onValueChange = { plot = it },
                    label = {
                        Text(
                            textAlign = TextAlign.Start,
                            text = stringResource(R.string.enter_plot)
                        )
                    },
                    isError = false
                )
            }

            Column() {
                OutlinedTextField(
                    value = rating,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        rating = if (it.startsWith("0")) {
                            ""
                        } else {
                            it
                        }
                        isRatingValid = viewModel.checkValidation(it,true)
                    },
                    label = { Text(stringResource(R.string.enter_rating)) },
                    isError = !isRatingValid
                )
                if (!isRatingValid) {
                    Text(text = "Rating is required and must be a number!", color = MaterialTheme.colors.error)
                }
            }

            isEnabledSaveButton = isTitleValid && isYearValid && isGenreValid && isDirectorValid
                    && isActorValid && isRatingValid

            id++

            val genreList: ArrayList<Genre> = ArrayList()

            for (i in genres.indices) {
                if(genreItems[i].isSelected){
                    genreList.add(genres[i])
                }
            }

            Button(
                enabled = isEnabledSaveButton,
                onClick = {
                    viewModel.addMovie( Movie(id = "${id}",title = title, year = year,
                    genre = genreList.joinToString(),
                    director = director, actors = actors, plot = plot,
                    images = listOf("https://t3.ftcdn.net/jpg/02/48/42/64/360_F_248426448_NVKLywWqArG2ADUxDq6QprtIzsF82dMF.jpg"),
                    rating = rating.toFloat(), initialChecked = false))

                    navController.navigate(route = Screen.Home.route)})
            {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}