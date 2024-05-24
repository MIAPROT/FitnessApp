package com.example.fitnessapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.db.IndividualExcercise
import org.jetbrains.exposed.sql.transactions.transaction

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddTrainings() {

    var training_name by remember { mutableStateOf("")}
    var training_link by remember { mutableStateOf("")}
    var training_timer by remember { mutableStateOf( "")}
    var training_description by remember { mutableStateOf("")}
    val youtubeLinkPrefix = "https://www.youtube.com/watch?v="
    Box(contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp),horizontalAlignment = Alignment.CenterHorizontally, ) {
            FlowRow(verticalArrangement = Arrangement.Center, horizontalArrangement = Arrangement.spacedBy(10.dp)){
                Text(
                    text = "Добавить упражнение", style = TextStyle(
                        fontSize = 30.sp,
                        lineHeight = 30.sp,
                        fontWeight = FontWeight(800),
                        textAlign = TextAlign.End,
                        letterSpacing = 0.1.sp
                    ), color = MaterialTheme.colorScheme.primary
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.spacedBy(10.dp) ) {
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = training_name,
                        onValueChange = { newText -> training_name = newText },
                        trailingIcon = {
                            Image(
                                Icons.Default.Close, contentDescription = null
                            )
                        },
                        label = { Text(text = "Название упражнения") })
                }
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = training_link,
                        onValueChange = { newText -> training_link = newText },
                        trailingIcon = {
                            Image(
                                Icons.Default.Close, contentDescription = null
                            )
                        },
                        label = { Text(text = "ссылка на YouTube") })
                }
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = training_timer,
                        onValueChange = { newText -> training_timer = newText },
                        trailingIcon = {
                            Image(
                                Icons.Default.Close, contentDescription = null
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        label = { Text(text = "Таймер") })
                }
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = training_description,
                        onValueChange = { newText -> training_description = newText },
                        trailingIcon = {
                            Image(
                                Icons.Default.Close, contentDescription = null
                            )
                        },
                        label = { Text(text = "Описание") })
                }
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                    Button(onClick = {
                        val cleanLink = training_link.replace(youtubeLinkPrefix, "")
                        transaction {
                            IndividualExcercise.new {
                                name =  training_name
                                muscular_id = 1
                                link = cleanLink
                                timer = training_timer.toInt()
                                image = ""
                                description = training_description
                            }
                        }
                    }) {
                        Text(text = "Сохранить")
                    }
                }
            }
        }
    }
}