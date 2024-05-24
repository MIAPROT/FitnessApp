package com.example.fitnessapp.models

data class ReadyWorkoutCardDTO(
    val name: String,
    val description: String,
    val image: String,
    val information: String,
    val listOfExercises: List<Int>,
    val id: Int
)