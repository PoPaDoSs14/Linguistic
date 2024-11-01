package com.example.linguistic.presentation

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.linguistic.data.RepositoryImpl

class UserStatsViewModel(application: Application): ViewModel() {

    val repo = RepositoryImpl(application)

    val userName: MutableState<String> = mutableStateOf("Имя пользователя")
    val knownWordsCount: MutableState<Int> = mutableStateOf(100)
    val userRating: MutableState<Int> = mutableStateOf(95)
    val avatarUri: MutableState<Uri?> = mutableStateOf(null)


    fun loadUserStats() {

    }
}