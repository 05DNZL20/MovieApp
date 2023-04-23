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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.data.MovieDatabase
import com.example.movieapp.models.*
import com.example.movieapp.repositories.MovieRepository
import com.example.movieapp.viewmodel.AddMovieViewModel
import com.example.movieapp.viewmodel.AddMovieViewModelFactory
import com.example.movieapp.viewmodel.FavoriteViewModel
import com.example.movieapp.viewmodel.FavoriteViewModelFactory
import com.example.movieapp.widgets.SimpleAppBar
import kotlinx.coroutines.launch

@Composable
fun AddMovieScreen(navController: NavController){
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao())
    val factory = AddMovieViewModelFactory(repository = repository)
    val viewModel: AddMovieViewModel = viewModel(factory = factory)

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
fun MainContent(navController: NavController, modifier: Modifier = Modifier, viewModel: AddMovieViewModel) {
    val coroutineScope = rememberCoroutineScope()

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


            Button(
                enabled = isEnabledSaveButton,
                onClick = {
                    coroutineScope.launch {
                        viewModel.addMovie(
                            Movie(
                                title = title, year = year,
                                genre = genreItems.filter { it.isSelected }.map { Genre.valueOf(it.title)},
                                director = director, actors = actors, plot = plot,
                                images = listOf("https://t3.ftcdn.net/jpg/02/48/42/64/360_F_248426448_NVKLywWqArG2ADUxDq6QprtIzsF82dMF.jpg"),
                                rating = rating.toFloat(), isFavorite = false
                            )
                        )
                    }
                    navController.navigate(route = Screen.Home.route)})
            {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}