package com.example.movieapp.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    Text(text = "Hello detailscreen $movieId")
}