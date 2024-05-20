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
import com.example.fitnessapp.db.DoneExcercise
import com.example.fitnessapp.db.IndividualExcercise
import com.example.fitnessapp.db.ReadyMadeWorkout
import com.example.fitnessapp.models.TrainingCardDTO
import org.jetbrains.exposed.sql.transactions.transaction

@Composable
fun HistoryScreen(navController: NavHostController) {
    val cardList = remember {
        mutableStateListOf<TrainingCardDTO>()
    }
    transaction {
        DoneExcercise.all().forEach() { exercise ->
            if (exercise.readyMadeWorkouts == null) {
                cardList.add(
                    TrainingCardDTO(
                        name = IndividualExcercise.findById(exercise.individualExcercises!!)!!.name,
                        description = "",
                        image = IndividualExcercise.findById(exercise.individualExcercises!!)!!.image,
                        showdate = false,
                        destonation = "",
                        timer = IndividualExcercise.findById(exercise.individualExcercises!!)!!.timer,
                        muscular_type = IndividualExcercise.findById(exercise.individualExcercises!!)!!.muscular_id
                            ?: 1,
                        link = IndividualExcercise.findById(exercise.individualExcercises!!)!!.link,
                        id = 0
                    )
                )
            } else {
                cardList.add(
                    TrainingCardDTO(
                        name = ReadyMadeWorkout.findById(exercise.readyMadeWorkouts!!)!!.name,
                        description = "",
                        image = ReadyMadeWorkout.findById(exercise.readyMadeWorkouts!!)!!.image,
                        showdate = false,
                        destonation = "",
                        timer = 0,
                        muscular_type = 1,
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