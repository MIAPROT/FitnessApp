package com.example.fitnessapp.models

import java.util.Date

data class ReadyWorkoutCardDTO(val name: String, val description: String, val image: String, val information: String, val listOfExcerises: List<Int>, val id: Int ) {
}