package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.Movie
import com.example.movieapp.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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