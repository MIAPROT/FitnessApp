package com.example.fitnessapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
fun FirstTimeScreen2()
{
    Box(Modifier.fillMaxSize()) {
        Text(modifier = Modifier.align(Alignment.Center),
            text = "Для начала тренировки мы должны узнать о вас побольше <3", style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(800),
                textAlign = TextAlign.Center,
                letterSpacing = 0.1.sp
            ), color = MaterialTheme.colorScheme.primary
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            shape = RectangleShape,
            onClick = { /*TODO*/ }) {
            Text(text = "ДАЛЕЕ")
        }

    }
}

@Preview(device = "id:pixel_7_pro", backgroundColor = 0xFFF32121)
@Composable
fun FirstTimePreview2(){
    FitnessAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            FirstTimeScreen2()
        }
    }
}