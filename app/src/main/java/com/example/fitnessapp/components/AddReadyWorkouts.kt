package com.example.fitnessapp.components
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.db.IndividualExcercise
import com.example.fitnessapp.db.Muscular_Types
import com.example.fitnessapp.db.ReadyMadeWorkout
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

@Composable
fun AddReadyWorkouts() {
    var allExercises by remember { mutableStateOf(listOf<IndividualExcercise>()) }
    var workoutNames by remember { mutableStateOf(listOf<String>()) }
    var muscularTypes by remember { mutableStateOf(listOf<String>()) }
    var trainingName by remember { mutableStateOf("") }
    var trainingDescription by remember { mutableStateOf("") }
    var selectedExercises by remember { mutableStateOf(listOf<IndividualExcercise>()) }

    LaunchedEffect(Unit) {
        val exercises = mutableListOf<IndividualExcercise>()
        val names = mutableListOf<String>()
        val types = mutableListOf<String>()

        transaction {
            IndividualExcercise.all().forEach { exercise ->
                exercises.add(exercise)
                val typeName = Muscular_Types.select { Muscular_Types.id eq exercise.muscular_id }
                    .singleOrNull()
                    ?.get(Muscular_Types.name)
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
                        if(isSelected) {
                            selectedExercises = selectedExercises + exercise
                        } else {
                            selectedExercises = selectedExercises.filter { ex -> ex != exercise }
                        }
                    }
                    .background(if (isSelected) Color.Gray else Color.White)
            ) {
                Text(
                    text = "$workoutName | $muscularType |",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(800),
                        letterSpacing = 0.1.sp
                    ),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = trainingName,
            onValueChange = { newText -> trainingName = newText },
            trailingIcon = {
                Image(
                    Icons.Default.Close, contentDescription = null
                )
            },
            label = { Text(text = "Название упражнения") })
    }
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = trainingDescription,
            onValueChange = { newText -> trainingDescription = newText },
            trailingIcon = {
                Image(
                    Icons.Default.Close, contentDescription = null
                )
            },
            label = { Text(text = "Описание") })
    }

    Button(
        onClick = {
            transaction {
                val selectedExercisesIds = selectedExercises.map { it.id.value }
                ReadyMadeWorkout.new {
                    name = trainingName
                    description = trainingDescription
                    image = ""
                    readyMadeWorkouts = SizedCollection(selectedExercisesIds.map { IndividualExcercise.findById(it)!! })
                }
            }
        }
    ) {
        Text(text = "Сохранить")
    }
}

