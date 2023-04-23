package com.example.movieapp.data

import androidx.room.*
import com.example.movieapp.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert
    suspend fun add(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Query("SELECT * FROM movie")
    fun readAllMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun readAllFav(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id=:movieId")
    fun getMovieById(movieId: Int): Movie
}