package com.example.linguistic.presentation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.linguistic.data.RepositoryImpl
import com.example.linguistic.presentation.screens.RegistrationScreen
import com.example.linguistic.presentation.screens.UserStatsScreen
import com.example.linguistic.presentation.screens.WordCardScreen

@Composable
fun LinguisticApp(
    application: Application,
    wordScreenViewModel: WordCardScreenViewModel,
    registrationViewModel: RegistrationViewModel,
    userStatsViewModel: UserStatsViewModel,
    navController: NavHostController
) {

    val registrationViewModel = registrationViewModel
    val userStatsViewModel = userStatsViewModel
    val wordScreenViewModel = wordScreenViewModel


    val words: List<Pair<String, String>> = listOf(
        "Hello" to "Привет",
        "Goodbye" to "До свидания",
        "Please" to "Пожалуйста",
        "Thank you" to "Спасибо",
        "Yes" to "Да",
        "No" to "Нет",
        "Sorry" to "Извини",
        "Help" to "Помощь"
    )



    NavHost(
        navController = navController,
        startDestination = "RegisterScreen"
    ) {
        composable("RegisterScreen") { RegistrationScreen(registrationViewModel, navController) }
        composable("UserStatsScreen") { UserStatsScreen(viewModel = userStatsViewModel) }
        composable("WordCardScreen") { WordCardScreen(words = words, wordScreenViewModel) }
    }

    if(registrationViewModel.getUser() != true){
        navController.navigate("WordCardScreen")
    }else{
        navController.navigate("RegisterScreen")
    }

}