package com.example.movieapp.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.screens.Screen


@Composable
fun SimpleAppBar(navController: NavController, text: String, homeOrNot: Boolean) {
    TopAppBar() {
        if(!homeOrNot)
        {
            ShowArrowBack(navController = navController)
        }

        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                style = MaterialTheme.typography.h6,
                text = text
            )
        }

        if(homeOrNot){
            ShowMoreVert(navController = navController)
        }
    }
}
@Composable
fun ShowArrowBack(navController: NavController){
    Row() {
        IconButton(onClick = { navController.navigate(route = Screen.Home.route) }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}
@Composable
fun ShowMoreVert(navController: NavController){
    var expanded by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Menu"
            )
            DropdownMenu(expanded = expanded,
                onDismissRequest = { expanded = !expanded },
            ) {
                DropdownMenuItem(onClick = { expanded = !expanded
                    navController.navigate(route = Screen.Favorite.route)}) {
                    Icon(imageVector = Icons.Default.Favorite,
                        contentDescription = "Favourites",
                        Modifier.padding(0.dp,0.dp,5.dp,0.dp))
                    Text(text = "Favourites")
                }
            }
        }
    }
}
