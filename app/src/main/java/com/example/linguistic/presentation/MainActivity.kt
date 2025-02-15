package com.example.linguistic.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.linguistic.data.RepositoryImpl
import com.example.linguistic.domain.Level
import com.example.linguistic.ui.theme.LinguisticTheme
import kotlinx.coroutines.coroutineScope

class MainActivity : ComponentActivity() {

    private lateinit var registrationViewModel: RegistrationViewModel
    private val userStatsViewModel: UserStatsViewModel by viewModels()
    private val wordScreen: WordCardScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        registrationViewModel.initializeSelectAvatarLauncher(this)
        setContent {
            LinguisticTheme {
                val navController = rememberNavController()
                LinguisticApp(
                    application = application,
                    navController = navController,
                    registrationViewModel = registrationViewModel,
                    userStatsViewModel = userStatsViewModel,
                    wordScreenViewModel = wordScreen
                )
            }
        }
    }
}

