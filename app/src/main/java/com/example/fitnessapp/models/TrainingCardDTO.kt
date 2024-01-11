package com.example.fitnessapp.models

import androidx.compose.runtime.Composable
import java.util.*
data class TrainingCardDTO(val name: String, val description: String, val image: String, val showdate: Boolean = false, val date: Date = Date(), val destonation: String, val timer: Int, val muscular_type: Int, val link: String, val id: Int )