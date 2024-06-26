package com.example.fitnessapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.models.BottomNavbarItem

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBarItemsList =
        listOf(
            BottomNavbarItem(
                Icons.Default.Favorite,
                "Тренировки"
            ) { TrainingScreen1(navController) },
            BottomNavbarItem(Icons.Default.Star, "История") { HistoryScreen(navController) },
            BottomNavbarItem(Icons.Default.AccountCircle, "Профиль") { AccountScreen() }
        )
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                navBarItemsList.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == screen.name } == true,
                        onClick = {
                            navController.navigate(screen.name) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(text = screen.name) })
                }
            }
        })
    { padding ->
        NavHost(
            navController = navController,
            startDestination = navBarItemsList[0].name,
            modifier = Modifier.padding(padding)
        ) {
            navBarItemsList.forEach { screen ->
                composable(screen.name) {
                    screen.composable()
                }
            }
            composable("TrainingScreen2") { TrainingScreen2() }
            composable("IndividualExcercisesScreen") { IndividualExercises() }
        }
    }


}

