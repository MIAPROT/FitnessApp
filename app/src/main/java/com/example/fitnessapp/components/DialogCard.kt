package com.example.fitnessapp.components

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogCard( onClick: () -> Unit, MainText: String, SecondaryText: String) {
    androidx.compose.material3.Card {
        Text(text = MainText)
        Text(
            text = SecondaryText,
            color = MaterialTheme.colorScheme.secondary
        )
        Button(onClick = { onClick() }) {
            Text(text = "Ок")
        }
    }

}
