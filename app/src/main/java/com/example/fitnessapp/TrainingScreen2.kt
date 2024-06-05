package com.example.fitnessapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.fitnessapp.components.AddButton
import com.example.fitnessapp.components.FilterReadyWorkouts
import com.example.fitnessapp.components.HugeCardList
import com.example.fitnessapp.db.Db
import com.example.fitnessapp.db.IndividualExercise
import com.example.fitnessapp.db.MuscularType
import com.example.fitnessapp.db.MuscularTypes
import com.example.fitnessapp.db.ReadyMadeWorkout
import com.example.fitnessapp.models.ReadyWorkoutCardDTO
import com.example.fitnessapp.models.TrainingCardDTO
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import kotlinx.datetime.Instant
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingScreen2() {
    Db
    var search by remember { mutableStateOf("") }
    var filter by remember { mutableStateOf(false) }
    var isSearchActive by remember { mutableStateOf(false) }
    var cardList = remember {
        mutableStateListOf<ReadyWorkoutCardDTO>()
    }
    var selectedExerciseType by remember { mutableStateOf<MuscularType?>(null) }

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
                                MuscularTypes
                                    .select { MuscularTypes.id eq muscularId }
                                    .singleOrNull()
                                    ?.get(MuscularTypes.name)
                                    ?.let { uniqueMuscularNames.add(it) }
                            }

                            uniqueMuscularNames.joinToString(", ")
                        },
                        listOfExercises = transaction {
                            ReadyMadeWorkout.findById(exercise.id)?.readyMadeWorkouts?.map { it.id.value }
                                ?.toList() ?: emptyList()
                        },
                        id = exercise.id.value
                    )
                )
                println(cardList[0].listOfExercises)
            }
        }
    }

    if (filter) {
        Dialog(onDismissRequest = { filter = false }) {
            Card(
                modifier = Modifier
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(Modifier.padding(16.dp)) {
                    FilterReadyWorkouts(
                        onSelectedExerciseTypeChange = { selectedExerciseType = it }
                    )
                }
            }
        }
        transaction {
            cardList.clear()
            ReadyMadeWorkout.all().forEach { exercise ->
                if (selectedExerciseType == null || exercise.readyMadeWorkouts.any { it.muscular_id == selectedExerciseType!!.id.value }) {
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
                                    MuscularTypes
                                        .select { MuscularTypes.id eq muscularId }
                                        .singleOrNull()
                                        ?.get(MuscularTypes.name)
                                        ?.let { uniqueMuscularNames.add(it) }
                                }

                                uniqueMuscularNames.joinToString(", ")
                            },
                            listOfExercises = transaction {
                                ReadyMadeWorkout.findById(exercise.id)?.readyMadeWorkouts?.map { it.id.value }
                                    ?.toList() ?: emptyList()
                            },
                            id = exercise.id.value
                        )
                    )
                }
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
                androidx.compose.material3.SearchBar(
                    query = search,
                    onQueryChange = { search = it },
                    onSearch = {
                        transaction {
                            cardList.clear()
                            ReadyMadeWorkout.all().forEach { exercise ->
                                if (selectedExerciseType == null || exercise.readyMadeWorkouts.any { it.muscular_id == selectedExerciseType!!.id.value }) {
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
                                                    MuscularTypes
                                                        .select { MuscularTypes.id eq muscularId }
                                                        .singleOrNull()
                                                        ?.get(MuscularTypes.name)
                                                        ?.let { uniqueMuscularNames.add(it) }
                                                }

                                                uniqueMuscularNames.joinToString(", ")
                                            },
                                            listOfExercises = transaction {
                                                ReadyMadeWorkout.findById(exercise.id)?.readyMadeWorkouts?.map { it.id.value }
                                                    ?.toList() ?: emptyList()
                                            },
                                            id = exercise.id.value
                                        )
                                    )
                                }
                            }
                        }
                        cardList = cardList.filter {
                            it.name.contains(search, ignoreCase = true)
                        }.toMutableStateList()
                        isSearchActive = false
                    },
                    active = isSearchActive,
                    onActiveChange = {
                        isSearchActive = !isSearchActive
                    },
                    placeholder = {
                        Text("Поиск")
                    },
                    leadingIcon = {
                        IconButton(
                            onClick = {
                                isSearchActive = !isSearchActive
                            }
                        ) {
                            Icon(
                                imageVector = if (isSearchActive) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Search,
                                contentDescription = null
                            )
                        }
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                filter = !filter
                            }
                        ) {
                            Icon(
                                imageVector = if (filter) Icons.Default.Close else Icons.Default.Settings,
                                contentDescription = null
                            )
                        }
                    }
                ) {

                }

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