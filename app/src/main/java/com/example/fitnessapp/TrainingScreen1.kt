package com.example.fitnessapp

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitnessapp.components.CardList
import com.example.fitnessapp.models.TrainingCardDTO

@Composable
fun TrainingScreen1(navController: NavHostController) {
    val cardList = remember {
        mutableStateListOf(
            TrainingCardDTO(
                "Готовые тренировки", "Тренировки до 30 минут",
                R.drawable.testimage, destonation = "TrainingScreen2", timer = 10, muscular_type = 1, link = ""
            ), TrainingCardDTO("Отдельные упражнения", "Упражнения", R.drawable.testimage, destonation = "IndividualExcercisesScreen", timer = 10, muscular_type = 1, link = "")
        )
    }
    CardList(cardList,
        Modifier
            .padding(24.dp), navController)

}