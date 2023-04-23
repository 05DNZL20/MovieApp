package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.Movie
import com.example.movieapp.repositories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: MovieRepository): ViewModel() {
    private val _favMovieList = MutableStateFlow(listOf<Movie>())
    val favMovieList: StateFlow<List<Movie>> = _favMovieList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.readAllFav().collect { favList ->
                _favMovieList.value = favList
            }
        }
    }

    suspend fun favoriteToggler(movie: Movie){
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }
}