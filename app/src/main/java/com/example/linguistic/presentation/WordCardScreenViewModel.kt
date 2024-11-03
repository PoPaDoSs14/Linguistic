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


    fun addKnowWord(){
        viewModelScope.launch(Dispatchers.IO) {
            val user = repo.getUser(1)
            user?.countOfWord?.plus(1)
        }

    }
}