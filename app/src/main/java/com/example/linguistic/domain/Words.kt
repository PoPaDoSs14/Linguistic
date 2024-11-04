package com.example.linguistic.domain

data class Words(
    val id: Int,
    val level: Level,
    val words: List<Pair<String, String>>
)
