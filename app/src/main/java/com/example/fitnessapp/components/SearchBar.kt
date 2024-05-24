package com.example.fitnessapp.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.db.IndividualExercise
import com.example.fitnessapp.models.TrainingCardDTO
import org.jetbrains.exposed.sql.transactions.transaction

@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit, modifier: Modifier, cardList: SnapshotStateList<TrainingCardDTO>) {
    OutlinedTextField(value = value,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyMedium,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.onTertiary,
            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
            focusedContainerColor = MaterialTheme.colorScheme.onTertiary
        ),
        singleLine = true,
        modifier = modifier.height(50.dp),
        placeholder = {
            Text(
                text = "Search"
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                val tempList = mutableListOf<TrainingCardDTO>()
                transaction {
                    IndividualExercise.all().forEach { exercise ->
                        if(exercise.name.contains(value, ignoreCase = true))
                        {
                            tempList.add(
                                TrainingCardDTO(
                                    name = exercise.name,
                                    description = exercise.description,
                                    image = exercise.image,
                                    showDate = false,
                                    destination = "",
                                    timer = exercise.timer,
                                    muscularType = exercise.muscular_id ?: 1,
                                    link = exercise.link,
                                    id = exercise.id.value
                                )
                            )
                        }
                    }
                }
                cardList.clear()
                cardList.addAll(tempList)
            }){
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search loupe",
                )
            }
        }
    )
}
