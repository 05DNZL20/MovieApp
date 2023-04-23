package com.example.movieapp.utils

import android.content.Context
import com.example.movieapp.data.MovieDatabase
import com.example.movieapp.viewmodel.AddMovieViewModelFactory
import com.example.movieapp.viewmodel.DetailViewModelFactory
import com.example.movieapp.viewmodel.FavoriteViewModelFactory
import com.example.movieapp.viewmodel.MoviesViewModelFactory
import com.example.movieapp.repositories.MovieRepository

object InjectorUtils {
    private fun getMovieRepository(context: Context): MovieRepository{
        return MovieRepository(MovieDatabase.getDatabase(context).movieDao())
    }

    fun provideMovieViewModelFactory(context: Context): MoviesViewModelFactory {
        val repository = getMovieRepository(context)
        return MoviesViewModelFactory(repository)
    }
    fun provideAddMovieViewModelFactory(context: Context): AddMovieViewModelFactory {
        val repository = getMovieRepository(context)
        return AddMovieViewModelFactory(repository)
    }
    fun provideDetailViewModelFactory(context: Context): FavoriteViewModelFactory {
        val repository = getMovieRepository(context)
        return FavoriteViewModelFactory(repository)
    }
    fun provideFavoriteViewModelFactory(context: Context): DetailViewModelFactory {
        val repository = getMovieRepository(context)
        return DetailViewModelFactory(repository)
    }
}