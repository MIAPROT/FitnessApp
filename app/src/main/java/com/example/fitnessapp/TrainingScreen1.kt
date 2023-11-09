package com.example.fitnessapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.components.CardList
import com.example.fitnessapp.models.TrainingCardDTO
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingScreen1() {
    val cardList = remember {
        mutableStateListOf(TrainingCardDTO("Готовые тренировки", "Тренировки до 30 минут",
            R.drawable.testimage), TrainingCardDTO("Отдельные упражнения", "Упражнения", R.drawable.testimage))
    }
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = { Icon(Icons.Default.Favorite, null) },
                    label = { Text(text = "Тренировки") })
                NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = {
                    Icon(Icons.Default.Star, null)
                }, label = { Text(text = "История") })
                NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                    Icon(Icons.Default.AccountCircle, null)
                }, label = { Text(text = "Профиль") })
            }
        })
    { padding ->
        CardList(cardList ,Modifier.padding(padding).padding(24.dp))
    }


}


@Preview
@Composable
fun TrainingPreview1() {
    FitnessAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            TrainingScreen1()
        }
    }
}