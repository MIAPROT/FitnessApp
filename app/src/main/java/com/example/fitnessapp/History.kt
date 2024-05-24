package com.example.fitnessapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitnessapp.components.CardList
import com.example.fitnessapp.db.DoneExercise
import com.example.fitnessapp.db.IndividualExercise
import com.example.fitnessapp.db.ReadyMadeWorkout
import com.example.fitnessapp.models.TrainingCardDTO
import org.jetbrains.exposed.sql.transactions.transaction

@Composable
fun HistoryScreen(navController: NavHostController) {
    val cardList = remember {
        mutableStateListOf<TrainingCardDTO>()
    }
    transaction {
        DoneExercise.all().forEach() { exercise ->
            if (exercise.readyMadeWorkouts == null) {
                cardList.add(
                    TrainingCardDTO(
                        name = IndividualExercise.findById(exercise.individualExcercises!!)!!.name,
                        description = "",
                        image = IndividualExercise.findById(exercise.individualExcercises!!)!!.image,
                        showDate = false,
                        destination = "",
                        timer = IndividualExercise.findById(exercise.individualExcercises!!)!!.timer,
                        muscularType = IndividualExercise.findById(exercise.individualExcercises!!)!!.muscular_id
                            ?: 1,
                        link = IndividualExercise.findById(exercise.individualExcercises!!)!!.link,
                        id = 0
                    )
                )
            } else {
                cardList.add(
                    TrainingCardDTO(
                        name = ReadyMadeWorkout.findById(exercise.readyMadeWorkouts!!)!!.name,
                        description = "",
                        image = ReadyMadeWorkout.findById(exercise.readyMadeWorkouts!!)!!.image,
                        showDate = false,
                        destination = "",
                        timer = 0,
                        muscularType = 1,
                        link = "",
                        id = 0
                    )
                )
            }
        }
    }
    Column() {
        CardList(
            cardList.reversed(),
            Modifier
                .padding(24.dp), navController
        )
    }


}