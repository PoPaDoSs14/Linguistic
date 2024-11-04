package com.example.linguistic.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguistic.data.RepositoryImpl
import com.example.linguistic.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordCardScreenViewModel(application: Application): AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)


    private var isUpdatingCount = false

    fun addKnowWord() {
        if (isUpdatingCount) return

        isUpdatingCount = true

        viewModelScope.launch(Dispatchers.IO) {
            val user = repo.getUser(1)
            if (user != null) {
                val updatedCount = user.countOfWord + 1
                user.countOfWord = updatedCount
                update(user)
            }
            isUpdatingCount = false
        }
    }

    suspend fun update(user: User) {
        repo.update(user)

    }


}