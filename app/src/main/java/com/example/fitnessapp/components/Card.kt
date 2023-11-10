package com.example.fitnessapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.R
import com.example.fitnessapp.models.TrainingCardDTO
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Card(card: TrainingCardDTO, modifier: Modifier ) {
    Card(
        modifier.padding(end = 10.dp, bottom = 10.dp).fillMaxWidth().shadow(4.dp, RoundedCornerShape(12.dp))
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline), RoundedCornerShape(12.dp))) {
        Row() {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(text = card.name, style = MaterialTheme.typography.titleMedium)
                if(card.showdate) {
                    Text(
                        text = card.date.toString(),
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                else{
                    Text(
                        text = card.description,
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            Image(
                painter = painterResource(id = card.image),
                contentDescription = null, modifier = Modifier.size(80.dp).align(CenterVertically),
            )
        }
    }
}

@Composable
@Preview
fun CardPreview() {
    FitnessAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Card(TrainingCardDTO("Тест2", "Тест", R.drawable.testimage,false, destonation = ""), Modifier)
        }
    }
}