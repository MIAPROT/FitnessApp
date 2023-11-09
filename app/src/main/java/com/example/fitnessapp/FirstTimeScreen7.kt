package com.example.fitnessapp


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstTimeScreen7() {
    var date = remember {
        mutableStateOf("")
    }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)
    var isVisible by remember {
        mutableStateOf(false)
    }
    Box(Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = "Ваша дата рождения?", style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(800),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp
                ), color = MaterialTheme.colorScheme.primary
            )
            OutlinedTextField(value = date.value, /*TODO*/ onValueChange = {newText -> date.value = newText}, trailingIcon = { Image(
                Icons.Default.Close, contentDescription = null)
            }, label = { Text(text = "Date")})
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            shape = RectangleShape,
            onClick = { /*TODO*/ }) {
            Text(text = "ДАЛЕЕ")
        }
        if (isVisible)
            AlertDialog(onDismissRequest = { isVisible = false }) {
                DatePicker(state = datePickerState)
            }
    }

}

@Preview(device = "id:pixel_7_pro", showBackground = true)
@Composable
fun FirstTimePreview7() {
    FitnessAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            FirstTimeScreen7()
        }
    }
}