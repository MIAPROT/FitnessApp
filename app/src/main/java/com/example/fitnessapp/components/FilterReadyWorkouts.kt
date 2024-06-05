package com.example.fitnessapp.components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import com.example.fitnessapp.db.MuscularType
import com.example.fitnessapp.db.MuscularTypes
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

@Composable
fun FilterReadyWorkouts(onSelectedExerciseTypeChange: (MuscularType?) -> Unit) {
    var allTypes by remember { mutableStateOf(listOf<MuscularType>()) }
    var muscularTypes by remember { mutableStateOf(listOf<String>()) }
    var trainingName by remember { mutableStateOf("") }
    var trainingDescription by remember { mutableStateOf("") }
    var selectedExercise by remember { mutableStateOf<MuscularType?>(null) }

    LaunchedEffect(Unit) {
        val muscularTypes = mutableListOf<MuscularType>()
        val types = mutableListOf<String>()

        transaction {
            MuscularType.all().forEach { muscularType ->
                muscularTypes.add(muscularType)
                val typeName = MuscularTypes.select { MuscularTypes.id eq muscularType.id }
                    .singleOrNull()
                    ?.get(MuscularTypes.name)
                typeName?.let { types.add(it) }
            }
        }
        allTypes = muscularTypes
    }

    LazyColumn {
        itemsIndexed(allTypes) { index, exercise ->
            val workoutName = exercise.name
            val isSelected = selectedExercise == exercise

            Row(
                modifier = Modifier
                    .clickable {
                        selectedExercise = if (isSelected) null else exercise
                        onSelectedExerciseTypeChange(selectedExercise)
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
            }
        }
    }

}