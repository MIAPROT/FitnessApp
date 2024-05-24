package com.example.fitnessapp.models

import java.util.*

data class TrainingCardDTO(
    val name: String,
    val description: String,
    val image: String,
    val showDate: Boolean = false,
    val date: Date = Date(),
    val destination: String,
    val timer: Int,
    val muscularType: Int,
    val link: String,
    val id: Int
)