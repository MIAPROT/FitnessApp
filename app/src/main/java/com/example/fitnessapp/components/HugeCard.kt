package com.example.fitnessapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.fitnessapp.db.Db
import com.example.fitnessapp.db.DoneExercise
import com.example.fitnessapp.db.IndividualExercise
import com.example.fitnessapp.db.ReadyMadeWorkout
import com.example.fitnessapp.models.ReadyWorkoutCardDTO
import com.example.fitnessapp.models.TrainingCardDTO
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import org.jetbrains.exposed.sql.transactions.transaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HugeCard(card: ReadyWorkoutCardDTO) {
    Db
    var isCalculatorVisible by remember {
        mutableStateOf(false)
    }
    val sampleItems = remember {
        mutableStateListOf<TrainingCardDTO>()
    }
    LaunchedEffect(null) {
        card.listOfExercises.forEach { exercise ->
            sampleItems.add(
                TrainingCardDTO(
                    name = transaction { IndividualExercise.findById(exercise)!!.name },
                    description = transaction { IndividualExercise.findById(exercise)!!.description },
                    image = "",
                    showDate = false,
                    destination = "",
                    timer = transaction { IndividualExercise.findById(exercise)!!.timer },
                    muscularType = transaction { IndividualExercise.findById(exercise)!!.muscular_id }
                        ?: 1,
                    link = transaction { IndividualExercise.findById(exercise)!!.link },
                    id = 0
                )
            )
        }
    }
    if (isCalculatorVisible)
        AlertDialog(onDismissRequest = { isCalculatorVisible = false }) {

            TrainingCardList(trainingInfoList = sampleItems)
        }
    androidx.compose.material3.Card(
        Modifier
            .padding(end = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .border(
                BorderStroke(1.dp, colorScheme.outline), RoundedCornerShape(12.dp)
            )
            .clickable {
                isCalculatorVisible = true
                transaction {
                    DoneExercise.new {
                        individualExcercises = null
                        readyMadeWorkouts = ReadyMadeWorkout.findById(card.id)!!.id
                    }
                }
            }
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            SubcomposeAsyncImage(
                model = card.image,
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .border(
                        BorderStroke(0.dp, colorScheme.outline),
                        RoundedCornerShape(8.dp)
                    ),
                contentScale = ContentScale.FillWidth
            )
            Text(text = card.name, style = typography.titleMedium)
            Text(
                text = card.description,
                style = typography.bodyMedium
            )
            Text(
                text = card.information,
                style = MaterialTheme.typography.bodySmall, color = colorScheme.primary
            )
        }

    }

}

@Preview
@Composable
fun HugeCardPreview() {
    FitnessAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = colorScheme.background) {
            HugeCard(
                ReadyWorkoutCardDTO(
                    "Кардио тренировка",
                    "Кардио тренировка дома",
                    "",
                    "Лёгкая кардио тренировка при помощи подручных средств. \n10 упражнений\n" +
                            "15 минут", listOfExercises = listOf(1, 3, 4), 1
                )
            )
        }
    }
}