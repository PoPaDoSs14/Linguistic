package com.example.linguistic.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.linguistic.domain.Level

@Entity("Words")
data class WordsDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val level: String,
    val words: String
)
