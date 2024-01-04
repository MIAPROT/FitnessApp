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
import com.example.fitnessapp.R
import com.example.fitnessapp.models.TrainingCardDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardList(cardList: List<TrainingCardDTO>, modifier: Modifier, navController: NavHostController) {
    var InformationVisibility by remember {
        mutableStateOf(false)
    }
    if (InformationVisibility)
        AlertDialog(onDismissRequest = { InformationVisibility = false }) {

            TrainingCard(TrainingCardDTO("Тест2", "Лягте на спину, согните ноги в коленях под углом 90 градусов, руки за головой, локти разведены в стороны\n" +
                    "Округлите спину и медленно усилием мышц живота поднимайте плечевой пояс по направлению к тазу. Старайтесь максимально напрячь мышцы пресса.", R.drawable.testimage,false, destonation = "", timer = 10, muscular_type = 1, link = "wEX1_NYoPls"))

        }
    LazyColumn(modifier = modifier) {
        items(cardList.size) {
            val card = cardList[it]
            Card(card,if(card.destonation.isNotEmpty()) Modifier.clickable { navController.navigate(card.destonation) } else Modifier.clickable { InformationVisibility = true })
        }
    }
}