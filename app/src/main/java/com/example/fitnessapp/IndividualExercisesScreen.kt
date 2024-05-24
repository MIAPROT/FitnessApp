package com.example.fitnessapp

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
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
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.components.AddButton
import com.example.fitnessapp.components.CardList
import com.example.fitnessapp.db.Db
import com.example.fitnessapp.db.IndividualExercise
import com.example.fitnessapp.models.TrainingCardDTO
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import org.jetbrains.exposed.sql.transactions.transaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndividualExercises() {

    Db
    var search by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    var cardList = remember {
        mutableStateListOf<TrainingCardDTO>()
    }
    LaunchedEffect(null) {
        transaction {
            IndividualExercise.all().forEach { exercise ->
                cardList.add(
                    TrainingCardDTO(
                        name = exercise.name,
                        description = exercise.description,
                        image = exercise.image,
                        showDate = false,
                        destination = "",
                        timer = exercise.timer,
                        muscularType = exercise.muscular_id,
                        link = exercise.link,
                        id = exercise.id.value
                    )
                )
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            AddButton("AddTrainings")
        },
        content = { paddingValues ->
            Column(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                    androidx.compose.material3.SearchBar(
                        query = search,
                        onQueryChange = { search = it },
                        onSearch = {
                            transaction {
                                cardList.clear()
                                IndividualExercise.all().forEach { exercise ->
                                    cardList.add(
                                        TrainingCardDTO(
                                            name = exercise.name,
                                            description = exercise.description,
                                            image = exercise.image,
                                            showDate = false,
                                            destination = "",
                                            timer = exercise.timer,
                                            muscularType = exercise.muscular_id,
                                            link = exercise.link,
                                            id = exercise.id.value
                                        )
                                    )
                                }
                            }
                            cardList = cardList.filter {
                                it.name.contains(search)
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
                        }
                    ) {

                    }
                    CardList(
                        cardList,
                        Modifier
                            .padding(24.dp),
                        rememberNavController()
                    )
                }
            }
        })

}


@Preview
@Composable
fun IndividualPreview() {
    FitnessAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            IndividualExercises()
        }
    }
}