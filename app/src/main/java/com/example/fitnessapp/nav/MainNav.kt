package com.example.fitnessapp.nav

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.FirstTimeScreenMain
import com.example.fitnessapp.MainScreen
import com.example.fitnessapp.db.DBTesting
import com.example.fitnessapp.db.DBTestingScreen

@Composable
fun MainNav() {
    val navController = rememberNavController()
    val activity = LocalContext.current as Activity
    var sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
    val checkVal = sharedPreferences.getBoolean("isFirstTime", true)
    NavHost(navController = navController, startDestination = if (checkVal) {
        with(sharedPreferences.edit()) {
            putBoolean("isFirstTime", false)
            apply()
            "FirstTimeScreenMain"
        }
    }
        else "MainScreen"
    ) {
        composable("FirstTimeScreenMain") {
            FirstTimeScreenMain(navController)
        }
        composable("MainScreen")
        {
            MainScreen()
        }
        composable("DBTesting"){ DBTesting() }

    }
}