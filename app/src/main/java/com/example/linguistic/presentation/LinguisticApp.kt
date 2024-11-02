package com.example.linguistic.presentation

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LinguisticApp(registrationViewModel: RegistrationViewModel,userStatsViewModel: UserStatsViewModel, application: Application,navController: NavHostController){

    val registrationViewModel = registrationViewModel
    val userStatsViewModel = userStatsViewModel

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


    NavHost(navController = navController, startDestination = "WordCardScreen") {
        composable("RegisterScreen") { RegistrationScreen(registrationViewModel, navController) }
        composable("UserStatsScreen") { UserStatsScreen(viewModel = userStatsViewModel)}
        composable("WordCardScreen") { WordCardScreen(words = words)}
    }

}