package com.example.linguistic.presentation

import android.app.Application
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguistic.data.RepositoryImpl
import com.example.linguistic.domain.Repository
import com.example.linguistic.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)
    private val _name = mutableStateOf("")
    val name: String get() = _name.value

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
        repo.addUser(User(0, name, avatarUri.toString(), 0, 0))
    }
}