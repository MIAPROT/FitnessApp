package com.example.fitnessapp

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.components.CardList
import com.example.fitnessapp.models.TrainingCardDTO

@Composable
fun TrainingScreen1() {
    val cardList = remember {
        mutableStateListOf(
            TrainingCardDTO(
                "Готовые тренировки", "Тренировки до 30 минут",
                R.drawable.testimage
            ), TrainingCardDTO("Отдельные упражнения", "Упражнения", R.drawable.testimage)
        )
    }
    CardList(cardList,
        Modifier
            .padding(24.dp))

}