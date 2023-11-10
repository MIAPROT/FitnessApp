package com.example.fitnessapp.models

import java.util.*
/** Пенес*/
data class TrainingCardDTO(val name: String, val description: String, val image: Int, val showdate: Boolean = false, val date: Date = Date() )