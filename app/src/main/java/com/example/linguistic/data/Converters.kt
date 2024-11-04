package com.example.linguistic.data

import androidx.room.TypeConverter
import com.example.linguistic.domain.Level

class Converters {

    @TypeConverter
    fun fromLevel(value: Level): String {
        return value.name
    }

    @TypeConverter
    fun toLevel(value: String): Level {
        return Level.valueOf(value)
    }

    @TypeConverter
    fun fromWordsList(value: List<Pair<String, String>>): String {
        return value.joinToString(separator = ";") { "${it.first},${it.second}" }
    }

    @TypeConverter
    fun toWordsList(value: String): List<Pair<String, String>> {
        return value.split(";").map {
            val (first, second) = it.split(",")
            Pair(first, second)
        }
    }
}