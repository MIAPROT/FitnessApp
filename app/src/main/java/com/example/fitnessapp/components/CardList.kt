package com.example.fitnessapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.fitnessapp.models.TrainingCardDTO

@Composable
fun CardList(cardList: List<TrainingCardDTO>, modifier: Modifier, navController: NavHostController) {
    LazyColumn(modifier = modifier) {
        items(cardList.size) {
            val card = cardList[it]
            Card(card,if(card.destonation.isNotEmpty()) Modifier.clickable { navController.navigate(card.destonation) } else Modifier)
        }
    }
}