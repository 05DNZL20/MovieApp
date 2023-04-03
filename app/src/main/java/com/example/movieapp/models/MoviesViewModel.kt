package com.example.movieapp.models

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel() {
    private val _movieList = getMovies().toMutableStateList()

    val movieList: List<Movie>
        get() = _movieList

    fun filterMovie(movieId: String): Movie {
        return movieList.filter { it.id == movieId}[0]
    }

    fun favoriteList(): List<Movie> {
        return movieList.filter { it.isFavorite }
    }

    fun favoriteToggler(movie: Movie){
        movieList.find { it.id == movie.id }?.let { _movie ->
            _movie.isFavorite = !_movie.isFavorite
        }
    }
}