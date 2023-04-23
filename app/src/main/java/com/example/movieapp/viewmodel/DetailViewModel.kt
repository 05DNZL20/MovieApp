package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.Genre
import com.example.movieapp.models.Movie
import com.example.movieapp.repositories.MovieRepository
import com.example.movieapp.screens.movieId
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MovieRepository): ViewModel() {

    fun filterMovie(movieId: Int): Movie {
        return repository.getMovieById(movieId)
    }

    suspend fun favoriteToggler(movie: Movie){
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }
}