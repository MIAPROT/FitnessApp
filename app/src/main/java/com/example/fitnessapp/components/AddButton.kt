package com.example.fitnessapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddButton(screen: String){
    var isaddvivsible by remember {
        mutableStateOf(false)
    }

    FloatingActionButton(
        onClick = { isaddvivsible = true},
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(Icons.Filled.Add, "Small floating action button.")
    }
    if (isaddvivsible)
        Dialog(onDismissRequest = { isaddvivsible = false }) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(16.dp)) {
                    if(screen == "AddReadyWorkouts")
                    {
                        AddReadyWorkouts()
                    }
                    else {
                        AddTrainings()
                    }
                    TextButton(onClick = {
                        isaddvivsible = false
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
            AddButton("AddTrainings")
        }
    }

}