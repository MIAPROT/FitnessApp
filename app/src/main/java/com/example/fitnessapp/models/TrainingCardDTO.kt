package com.example.fitnessapp.models

import androidx.compose.runtime.Composable
import java.util.*
data class TrainingCardDTO(val name: String, val description: String, val image: Int, val showdate: Boolean = false, val date: Date = Date(), val destonation: String   )