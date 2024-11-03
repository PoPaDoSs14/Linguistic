package com.example.linguistic.presentation

import android.app.Application
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

    val words: List<Pair<String, String>> = listOf(
        Pair("Apple", "Яблоко"),
        Pair("Orange", "Апельсин"),
        Pair("Banana", "Банан"),
        Pair("Grape", "Виноград"),
        Pair("Strawberry", "Клубника"),
        Pair("Watermelon", "Арбуз"),
        Pair("Pineapple", "Ананас"),
        Pair("Peach", "Персик"),
        Pair("Cherry", "Вишня"),
        Pair("Lemon", "Лимон"),
        Pair("Mango", "Манго"),
        Pair("Blueberry", "Черника"),
        Pair("Raspberry", "Малина"),
        Pair("Kiwi", "Киви"),
        Pair("Coconut", "Кокос")
    )

    val isUserRegistered by registrationViewModel.isUserRegistered.observeAsState(initial = null)


    NavHost(
        navController = navController,
        startDestination = "RegisterScreen"
    ) {
        composable("RegisterScreen") { RegistrationScreen(registrationViewModel, navController) }
        composable("UserStatsScreen") { UserStatsScreen(viewModel = userStatsViewModel) }
        composable("WordCardScreen") {
            WordCardScreen(
                words = words,
                viewModel = wordScreenViewModel,
                navController = navController,
                Level.EASY
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