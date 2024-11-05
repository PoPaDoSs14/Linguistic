package com.example.linguistic.presentation

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.linguistic.data.RepositoryImpl
import com.example.linguistic.domain.Level
import com.example.linguistic.domain.Words
import com.example.linguistic.presentation.screens.RegistrationScreen
import com.example.linguistic.presentation.screens.UserStatsScreen
import com.example.linguistic.presentation.screens.WordCardScreen

@Composable
fun LinguisticApp(
    application: Application,
    wordScreenViewModel: WordCardScreenViewModel,
    registrationViewModel: RegistrationViewModel,
    userStatsViewModel: UserStatsViewModel,
    navController: NavHostController,
    level: Level
) {

    wordScreenViewModel.loadingWords()

    LaunchedEffect(Unit) {
        wordScreenViewModel.getWords("EASY")
        wordScreenViewModel.getWords("MEDIUM")
        wordScreenViewModel.getWords("HARD")
    }

    val easyWords = wordScreenViewModel.easyWords.value
    val normalWords = wordScreenViewModel.normalWords.value
    val hardWords = wordScreenViewModel.hardWords.value


    val words = if (level == Level.EASY) easyWords else if(level == Level.MEDIUM) normalWords else hardWords

    val isUserRegistered by registrationViewModel.isUserRegistered.observeAsState(initial = null)


    NavHost(
        navController = navController,
        startDestination = "RegisterScreen"
    ) {
        composable("RegisterScreen") { RegistrationScreen(registrationViewModel, navController) }
        composable("UserStatsScreen") { UserStatsScreen(viewModel = userStatsViewModel) }
        composable("WordCardScreen") {
            WordCardScreen(
                easyWords = words?.words?.toMutableList() ?: mutableListOf(),
                viewModel = wordScreenViewModel,
                navController = navController,
                level,
                userStatsViewModel
            )
        }
    }

    LaunchedEffect(Unit) {
        registrationViewModel.checkUser()
    }

    when {
        isUserRegistered == null -> {
            CircularProgressIndicator()
        }
        isUserRegistered!! -> {
            navController.navigate("WordCardScreen") {
                popUpTo("RegisterScreen") { inclusive = true }
            }
        }
        else -> {
            navController.navigate("RegisterScreen")
        }
    }
}