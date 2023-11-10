package com.example.fitnessapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.components.CardList
import com.example.fitnessapp.models.TrainingCardDTO
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
fun IndividualExercises() {
    val cardList = remember {
        mutableStateListOf(
            TrainingCardDTO(
                "Жим ногами", "Квадрицепс, мышцы бедра и ягодиц",
                R.drawable.testimage, false, destonation = ""
            ),
            TrainingCardDTO(
                "Подтягивания средний хват",
                "трапеция, широчайшая",
                R.drawable.testimage,
                false, destonation = ""
            ),
            TrainingCardDTO(
                "Подтягивания средний хват",
                "трапеция, широчайшая",
                R.drawable.testimage,
                false, destonation = ""
            ),
            TrainingCardDTO(
                "Подтягивания средний хват",
                "трапеция, широчайшая",
                R.drawable.testimage,
                false, destonation = ""
            ),
            TrainingCardDTO(
                "Подтягивания средний хват",
                "трапеция, широчайшая",
                R.drawable.testimage,
                false, destonation = ""
            ),
            TrainingCardDTO(
                "Подтягивания средний хват",
                "трапеция, широчайшая",
                R.drawable.testimage,
                false, destonation = ""
            ),
            TrainingCardDTO(
                "Подтягивания средний хват",
                "трапеция, широчайшая",
                R.drawable.testimage,
                false, destonation = ""
            ),
            TrainingCardDTO(
                "Подтягивания средний хват",
                "трапеция, широчайшая",
                R.drawable.testimage,
                false, destonation = ""
            )
        )
    }

    CardList(
        cardList,
        Modifier
            .padding(24.dp),
        rememberNavController()
    )

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