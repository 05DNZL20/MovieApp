package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieapp.models.Movie
import com.example.movieapp.repositories.MovieRepository

class AddMovieViewModel(private val repository: MovieRepository): ViewModel() {
    fun checkValidation(input: String, float: Boolean): Boolean {
        if (!float) {
            return input.isNotEmpty()
        }else{
            return input.isNotEmpty()&&input.toFloatOrNull() != null
        }
    }

    suspend fun addMovie(movie: Movie){
        repository.add(movie)
    }
}