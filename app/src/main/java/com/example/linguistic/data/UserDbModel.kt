package com.example.linguistic.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Users")
data class UserDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val avatar: String,
    val countOfWord: Int,
    val rating: Int
)
