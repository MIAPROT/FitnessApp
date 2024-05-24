package com.example.fitnessapp.components

import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.db.IndividualExercise
import com.example.fitnessapp.db.MuscularTypes
import com.example.fitnessapp.db.ReadyMadeWorkout
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

@Composable
fun AddReadyWorkouts() {
    var allExercises by remember { mutableStateOf(listOf<IndividualExercise>()) }
    var workoutNames by remember { mutableStateOf(listOf<String>()) }
    var muscularTypes by remember { mutableStateOf(listOf<String>()) }
    var trainingName by remember { mutableStateOf("") }
    var trainingDescription by remember { mutableStateOf("") }
    var selectedExercises by remember { mutableStateOf(listOf<IndividualExercise>()) }

    LaunchedEffect(Unit) {
        val exercises = mutableListOf<IndividualExercise>()
        val names = mutableListOf<String>()
        val types = mutableListOf<String>()

        transaction {
            IndividualExercise.all().forEach { exercise ->
                exercises.add(exercise)
                val typeName = MuscularTypes.select { MuscularTypes.id eq exercise.muscular_id }
                    .singleOrNull()
                    ?.get(MuscularTypes.name)
                typeName?.let { types.add(it) }
            }
        }
        allExercises = exercises

        workoutNames = names
        muscularTypes = types
    }

    LazyColumn {
        itemsIndexed(allExercises) { index, exercise ->
            val workoutName = exercise.name
            val muscularType = muscularTypes.getOrNull(index) ?: ""
            var isSelected by remember { mutableStateOf(false) }

            Row(
                modifier = Modifier
                    .clickable {
                        isSelected = !isSelected
                        selectedExercises = if (isSelected) {
                            selectedExercises + exercise
                        } else {
                            selectedExercises.filter { ex -> ex != exercise }
                        }
                    }
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    workoutName,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium,
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                )
                AssistChip(
                    label = {
                        Text(
                            muscularType, style = MaterialTheme.typography.labelLarge,
                            color = if (isSelected) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.tertiary
                        )
                    },
                    onClick = {}
                )
            }
        }
    }
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = trainingName,
            onValueChange = { newText -> trainingName = newText },
            trailingIcon = {
                Image(
                    Icons.Default.Close, contentDescription = null
                )
            },
            label = { Text(text = "Название упражнения") })
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = trainingDescription,
            onValueChange = { newText -> trainingDescription = newText },
            trailingIcon = {
                Image(
                    Icons.Default.Close, contentDescription = null
                )
            },
            label = { Text(text = "Описание") })
        Button(
            onClick = {
                transaction {
                    val selectedExercisesIds = selectedExercises.map { it.id.value }
                    ReadyMadeWorkout.new {
                        name = trainingName
                        description = trainingDescription
                        image = ""
                        readyMadeWorkouts = SizedCollection(selectedExercisesIds.map {
                            IndividualExercise.findById(it)!!
                        })
                    }
                }
            }
        ) {
            Text(text = "Сохранить")
        }
    }
}

