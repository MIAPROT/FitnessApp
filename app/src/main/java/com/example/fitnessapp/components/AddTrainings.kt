package com.example.fitnessapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.db.IndividualExercise
import org.jetbrains.exposed.sql.transactions.transaction

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddTrainings() {

    var trainingName by remember { mutableStateOf("") }
    var trainingLink by remember { mutableStateOf("") }
    var trainingTimer by remember { mutableStateOf("") }
    var trainingDescription by remember { mutableStateOf("") }
    val youtubeLinkPrefix = "https://www.youtube.com/watch?v="
    Box(contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            FlowRow(
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Добавить упражнение",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
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
                        value = trainingLink,
                        onValueChange = { newText -> trainingLink = newText },
                        trailingIcon = {
                            Image(
                                Icons.Default.Close, contentDescription = null
                            )
                        },
                        label = { Text(text = "Ссылка на YouTube") })
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = trainingTimer,
                        onValueChange = { newText -> trainingTimer = newText },
                        trailingIcon = {
                            Image(
                                Icons.Default.Close, contentDescription = null
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        label = { Text(text = "Таймер") })
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
                Button(onClick = {
                    val cleanLink = trainingLink.replace(youtubeLinkPrefix, "")
                    transaction {
                        IndividualExercise.new {
                            name = trainingName
                            muscular_id = 1
                            link = cleanLink
                            timer = trainingTimer.toInt()
                            image = ""
                            description = trainingDescription
                        }
                    }
                }) {
                    Text(text = "Сохранить")
                }
            }
        }
    }
}