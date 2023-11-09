package com.example.fitnessapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.R
import com.example.fitnessapp.models.ReadyWorkoutCardDTO
import com.example.fitnessapp.models.TrainingCardDTO
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HugeCard(card: ReadyWorkoutCardDTO) {
    androidx.compose.material3.Card(
        Modifier
            .padding(end = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .border(
                BorderStroke(1.dp, colorScheme.outline), RoundedCornerShape(12.dp)
            )
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Image(
                painter = painterResource(id = card.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                contentScale = ContentScale.FillWidth
            )
            Text(text = card.name, style = typography.titleMedium)
            Text(
                text = card.description.toString(),
                style = typography.labelSmall
            )
            Text(text = card.information, style = typography.labelSmall, modifier = Modifier.padding(top = 16.dp))
        }

    }

}

@Preview
@Composable
fun HugeCardPreview() {
    FitnessAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = colorScheme.background) {
            HugeCard(
                ReadyWorkoutCardDTO(
                    "Кардио тренировка",
                    "Кардио тренировка дома",
                    R.drawable.testimage,
                    "Лёгкая кардио тренировка при помощи подручных средств. \n10 упражнений\n" +
                            "15 минут"
                )
            )
        }
    }
}