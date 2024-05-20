package com.example.fitnessapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.components.AddButton
import com.example.fitnessapp.components.CardList
import com.example.fitnessapp.components.HugeCardList
import com.example.fitnessapp.components.SearchBar
import com.example.fitnessapp.db.Db
import com.example.fitnessapp.db.Muscular_Types
import com.example.fitnessapp.db.ReadyMadeWorkout
import com.example.fitnessapp.models.ReadyWorkoutCardDTO
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingScreen2() {
    Db
    val cardList = remember {
        mutableStateListOf<ReadyWorkoutCardDTO>()
    }
    LaunchedEffect(null) {
        transaction {
            ReadyMadeWorkout.all().forEach() { exercise ->
                cardList.add(
                    ReadyWorkoutCardDTO(
                        name = exercise.name,
                        description = exercise.description,
                        image = exercise.image,
                        information = transaction {
                            val muscularIds =
                                ReadyMadeWorkout.findById(exercise.id)?.readyMadeWorkouts?.map { it.muscular_id }
                            val uniqueMuscularNames = mutableSetOf<String>()

                            muscularIds?.forEach { muscularId ->
                                Muscular_Types
                                    .select { Muscular_Types.id eq muscularId }
                                    .singleOrNull()
                                    ?.get(Muscular_Types.name)
                                    ?.let { uniqueMuscularNames.add(it) }
                            }

                            uniqueMuscularNames.joinToString(", ")
                        },
                        listOfExcerises = transaction {
                            ReadyMadeWorkout.findById(exercise.id)?.readyMadeWorkouts?.map { it.id.value }
                                ?.toList() ?: emptyList()
                        },
                        id = exercise.id.value
                    )
                )
                println(cardList[0].listOfExcerises)
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            AddButton("AddReadyWorkouts")
        },
        content = { paddingValues ->
            Column(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                HugeCardList(cardList, Modifier.padding(24.dp))

            }

        })



}


@Preview
@Composable
fun TrainingPreview2() {
    FitnessAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            TrainingScreen2()
        }
    }
}