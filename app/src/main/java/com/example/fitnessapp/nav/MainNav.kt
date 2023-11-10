package com.example.fitnessapp.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.FirstTimeScreenMain
import com.example.fitnessapp.MainScreen

@Composable
fun MainNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "FirstTimeScreenMain"){
        composable("FirstTimeScreenMain"){
            FirstTimeScreenMain(navController)
        }
        composable("MainScreen")
        {
            MainScreen()
        }
    }
}