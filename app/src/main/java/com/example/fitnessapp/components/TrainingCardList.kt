package com.example.fitnessapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.R
import com.example.fitnessapp.models.TrainingCardDTO
import kotlin.math.max
import kotlin.math.min


@Composable
fun TrainingCardList(trainingInfoList: List<TrainingCardDTO>) {
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
    ) {
        // Training Card
        TrainingCard(trainingInfo = trainingInfoList[currentIndex])

        // Indicator and Navigation Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Navigation Arrows
            IconButton(
                onClick = {
                    currentIndex = max(currentIndex - 1, 0)
                },
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = LocalContentColor.current
                )
            }
            LinearProgressIndicator( modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),progress = (currentIndex + 1).toFloat() / trainingInfoList.size)


            IconButton(
                onClick = {
                    currentIndex = min(currentIndex + 1, trainingInfoList.size - 1)
                },
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = LocalContentColor.current
                )
            }

        }
    }
}
