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
    navController: NavHostController,
    level: Level
) {

    val easyWords: List<Pair<String, String>> = listOf(
        Pair("Cat", "Кот"),
        Pair("Dog", "Собака"),
        Pair("Sun", "Солнце"),
        Pair("Moon", "Луна"),
        Pair("Tree", "Дерево"),
        Pair("Water", "Вода"),
        Pair("House", "Дом"),
        Pair("Book", "Книга"),
        Pair("Chair", "Стул"),
        Pair("Table", "Стол")
    )

    val normalWords: List<Pair<String, String>> = listOf(
        Pair("School", "Школа"),
        Pair("Friend", "Друг"),
        Pair("Family", "Семья"),
        Pair("Happy", "Счастливый"),
        Pair("Music", "Музыка"),
        Pair("Travel", "Путешествие"),
        Pair("City", "Город"),
        Pair("Animal", "Животное"),
        Pair("Food", "Еда"),
        Pair("Market", "Рынок")
    )

    val hardWords: List<Pair<String, String>> = listOf(
        Pair("Philosophy", "Философия"),
        Pair("Antidisestablishmentarianism", "Антидизестаблишментаризм"),
        Pair("Constitutional", "Конституционный"),
        Pair("Metamorphosis", "Метаморфоза"),
        Pair("Pneumonoultramicroscopicsilicovolcanoconiosis", "Пневмокониоз"),
        Pair("Cryptocurrency", "Криптовалюта"),
        Pair("Psychotherapy", "Психотерапия"),
        Pair("Electromagnetism", "Электромагнетизм"),
        Pair("Bureaucracy", "Бюрократия"),
        Pair("Incomprehensible", "Непонятный")
    )

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
                words = words,
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