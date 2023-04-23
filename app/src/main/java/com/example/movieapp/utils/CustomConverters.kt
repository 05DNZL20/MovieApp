package com.example.movieapp.utils

import androidx.room.TypeConverter
import com.example.movieapp.models.Genre

class CustomConverters {

    @TypeConverter
    fun listToString(value: List<String>) = value.joinToString(",")

    @TypeConverter
    fun stringToList(value: String) = value.split(",").map { it.trim() }

    @TypeConverter
    fun enumListToString(value: List<Genre>) = value.joinToString(",")

    @TypeConverter
    fun stringToEnumList(value: String) = value.split(",").map { Genre.valueOf(it.trim()) }
}