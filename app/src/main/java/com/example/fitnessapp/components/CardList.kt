package com.example.fitnessapp.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fitnessapp.models.TrainingCardDTO

@Composable
fun CardList(cardList: List<TrainingCardDTO>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(cardList.size) {
            val card = cardList[it]
            Card(card)
        }
    }
}