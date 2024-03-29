package com.example.fitnessapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp.AccountScreen
import com.example.fitnessapp.AddTrainings
import com.example.fitnessapp.CalculatorScreen
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
fun AddButton(){
    var isCalculatorVisible by remember {
        mutableStateOf(false)
    }
    FloatingActionButton(
        onClick = { isCalculatorVisible = true},
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(Icons.Filled.Add, "Small floating action button.")
    }
    if (isCalculatorVisible)
        AlertDialog(onDismissRequest = { isCalculatorVisible = false }) {
            Surface {
                Column {
                    AddTrainings()
                    Button(onClick = {
                        isCalculatorVisible = false
                    }) {
                        Text(text = "Выйти")
                    }
                }
            }

        }
}

@Preview
@Composable
fun AddButtonPreview() {
    FitnessAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AddButton()
        }
    }

}