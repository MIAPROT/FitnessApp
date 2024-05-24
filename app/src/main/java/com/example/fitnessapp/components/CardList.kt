package com.example.fitnessapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.fitnessapp.db.Db
import com.example.fitnessapp.db.DoneExercise
import com.example.fitnessapp.db.IndividualExercise
import com.example.fitnessapp.models.TrainingCardDTO
import org.jetbrains.exposed.sql.transactions.transaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardList(
    cardList: List<TrainingCardDTO>,
    modifier: Modifier,
    navController: NavHostController
) {
    Db
    var dialogCard: TrainingCardDTO? by remember {
        mutableStateOf(null)
    }
    var informationVisibility by remember {
        mutableStateOf(false)
    }
    if (informationVisibility)
        AlertDialog(onDismissRequest = { informationVisibility = false }) {
            TrainingCard(dialogCard!!)
        }

    LazyColumn(modifier = modifier) {
        items(cardList.size) {
            val card = cardList.getOrNull(it) ?: return@items
            CustomCard(
                card,
                if (card.destination.isNotEmpty()) Modifier.clickable { navController.navigate(card.destination) } else Modifier.clickable {
                    informationVisibility = true
                    dialogCard = card
                    transaction {
                        DoneExercise.new {
                            individualExcercises = IndividualExercise.findById(card.id)!!.id
                            readyMadeWorkouts = null
                        }
                    }
                })
        }
    }
}