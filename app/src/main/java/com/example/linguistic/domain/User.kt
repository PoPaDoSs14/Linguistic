package com.example.linguistic.domain

import android.net.Uri

data class User (
    val id: Int,
    val name: String,
    val avatar: String,
    var countOfWord: Int,
    val rating: Int
)
