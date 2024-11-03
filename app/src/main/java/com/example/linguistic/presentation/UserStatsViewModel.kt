package com.example.linguistic.presentation

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguistic.data.RepositoryImpl
import com.example.linguistic.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserStatsViewModel(application: Application): AndroidViewModel(application) {

    val repo = RepositoryImpl(application)

    val userName: MutableState<String> = mutableStateOf("Имя пользователя")
    val knownWordsCount: MutableState<Int> = mutableStateOf(100)
    val userRating: MutableState<Int> = mutableStateOf(95)
    val avatarUri: MutableState<Uri?> = mutableStateOf(null)


    fun loadUserStats() {
        viewModelScope.launch(Dispatchers.IO) {
            userName.value = repo.getUser(1)?.name ?: "Имя пользователя"
            knownWordsCount.value = repo.getUser(1)?.countOfWord ?: 100
            userRating.value = repo.getUser(1)?.rating ?: 95
            avatarUri.value = repo.getUser(1)?.avatar?.toUri() ?: null
        }
    }
}