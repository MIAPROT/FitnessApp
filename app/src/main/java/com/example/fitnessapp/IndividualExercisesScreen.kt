package com.example.fitnessapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.components.AddButton
import com.example.fitnessapp.components.CardList
import com.example.fitnessapp.components.SearchBar
import com.example.fitnessapp.db.Db
import com.example.fitnessapp.db.IndividualExcercise
import com.example.fitnessapp.db.IndividualExcercises.link
import com.example.fitnessapp.db.IndividualExcercises.timer
import com.example.fitnessapp.models.TrainingCardDTO
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import org.jetbrains.exposed.sql.transactions.transaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndividualExercises() {

    var isCalculatorVisible by remember {
        mutableStateOf(false)
    }

    Db
    var search by remember { mutableStateOf("") }

    val cardList = remember {
        mutableStateListOf<TrainingCardDTO>()
    }
    LaunchedEffect(null) {
        transaction {
            IndividualExcercise.all().forEach() { exercise ->
                cardList.add(
                    TrainingCardDTO(
                        name = exercise.name,
                        description = exercise.description,
                        image = exercise.image,
                        showdate = false,
                        destonation = "",
                        timer = exercise.timer,
                        muscular_type = exercise.muscular_id ?: 1,
                        link = exercise.link,
                        id = exercise.id.value
                    )
                )
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            AddButton()
        },
        content = { paddingValues ->
            Column(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                    SearchBar(
                        value = search,
                        onValueChange = { newText -> search = newText },
                        modifier = Modifier
                            .height(50.dp),
                        cardList
                    )

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