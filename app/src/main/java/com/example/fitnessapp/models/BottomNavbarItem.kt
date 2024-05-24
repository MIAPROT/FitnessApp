package com.example.fitnessapp.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavbarItem(
    val icon: ImageVector,
    val name: String,
    val composable: @Composable () -> Unit
)
