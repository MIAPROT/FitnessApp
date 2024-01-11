package com.example.fitnessapp

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitnessapp.components.CardList
import com.example.fitnessapp.db.DBTesting
import com.example.fitnessapp.models.TrainingCardDTO

@Composable
fun TrainingScreen1(navController: NavHostController) {
    val cardList = remember {
        mutableStateListOf(
            TrainingCardDTO(
                "Готовые тренировки",
                "Тренировки до 30 минут",
                "https://fitstars.ru/storage/app/uploads/public/615/58c/8f9/61558c8f95c16556275289.webp",
                destonation = "TrainingScreen2",
                timer = 10,
                muscular_type = 1,
                link = "",
                id = 0
            ),
            TrainingCardDTO(
                "Отдельные упражнения",
                "Упражнения",
                "https://mykaleidoscope.ru/uploads/posts/2022-08/1660584690_18-mykaleidoscope-ru-p-fitnes-trenirovka-doma-dizain-krasivo-foto-20.jpg",
                destonation = "IndividualExcercisesScreen",
                timer = 10,
                muscular_type = 1,
                link = "",
                id = 0
            )
        )
    }
    CardList(
        cardList,
        Modifier
            .padding(24.dp), navController
    )

}