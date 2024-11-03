package com.example.linguistic.presentation

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguistic.data.RepositoryImpl
import com.example.linguistic.domain.Repository
import com.example.linguistic.domain.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)
    private val _name = mutableStateOf("")
    val name: String get() = _name.value

    private val _isUserRegistered = MutableLiveData<Boolean>()
    val isUserRegistered: LiveData<Boolean> get() = _isUserRegistered

    private val _avatarUri: MutableState<Uri?> = mutableStateOf(null)
    val avatarUri: Uri? get() = _avatarUri.value

    private lateinit var selectAvatarLauncher: ActivityResultLauncher<String>

    fun initializeSelectAvatarLauncher(activity: ComponentActivity) {
        selectAvatarLauncher = activity.registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let { _avatarUri.value = it }
        }
    }

    fun checkUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val user = repo.getUser(1)
                _isUserRegistered.postValue(user != null)
            } catch (e: Exception) {
                // Обработка исключения
                Log.e("RegistrationViewModel", "Error checking user", e)
                _isUserRegistered.postValue(false)
            }
        }
    }

    fun selectAvatar() {
        selectAvatarLauncher.launch("image/*")
    }

    fun registerUser(name: String) {
        viewModelScope.launch {
            if (name.isNotBlank() && avatarUri != null) {

                saveUserToDatabase(name, avatarUri!!)
            } else {

            }
        }
    }

    private suspend fun saveUserToDatabase(name: String, avatarUri: Uri) {
        viewModelScope.launch(Dispatchers.IO){
            repo.addUser(User(0, name, avatarUri.toString(), 0, 0))
        }
    }
}