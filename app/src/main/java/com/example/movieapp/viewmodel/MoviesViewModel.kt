package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.Movie
import com.example.movieapp.repositories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow(listOf<Movie>())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            repository.readAllMovies().collect{ movieList ->
                    _movies.value = movieList
            }
        }
    }

    suspend fun favoriteToggler(movie: Movie){
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }

    suspend fun deleteMovie(movie: Movie){
        repository.delete(movie)
    }
}