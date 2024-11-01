package com.example.linguistic.presentation

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LinguisticApp(viewModel: RegistrationViewModel, application: Application,navController: NavHostController){

    val registrationViewModel = viewModel

    NavHost(navController = navController, startDestination = "RegisterScreen") {
        composable("RegisterScreen") { RegistrationScreen(registrationViewModel) }
    }

}