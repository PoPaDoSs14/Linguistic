package com.example.linguistic.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.linguistic.ui.theme.LinguisticTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        viewModel.initializeSelectAvatarLauncher(this)
        setContent {
            LinguisticTheme {
                val navController = rememberNavController()
                LinguisticApp(application = application, navController = navController, viewModel = viewModel)
            }
        }
    }
}

