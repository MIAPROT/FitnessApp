package com.example.fitnessapp

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen() {
    var weight = remember {
        mutableStateOf("")
    }
    var height = remember {
        mutableStateOf("")
    }
    var date = remember {
        mutableStateOf("")
    }
    var isVisible by remember {
        mutableStateOf(false)
    }
    var dailyActive by remember { mutableStateOf(0f) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        Column {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Дата рождения", style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(800),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp
                ), color = MaterialTheme.colorScheme.primary
            )
            OutlinedTextField(
                value = date.value, /*TODO*/
                onValueChange = { newText -> date.value = newText },
                trailingIcon = {
                    IconButton(onClick = { isVisible = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = null)
                    }
                },
                label = { Text(text = "Date") })
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Вес", style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(800),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp
                ), color = MaterialTheme.colorScheme.primary
            )
            OutlinedTextField(
                value = weight.value,
                onValueChange = { newText -> weight.value = newText },
                trailingIcon = {
                    Image(
                        Icons.Default.Close, contentDescription = null
                    )
                },
                label = { Text(text = "Weight") })
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Рост", style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(800),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp
                ), color = MaterialTheme.colorScheme.primary
            )
            OutlinedTextField(
                value = height.value,
                onValueChange = { newText -> height.value = newText },
                trailingIcon = {
                    Image(
                        Icons.Default.Close, contentDescription = null
                    )
                },
                label = { Text(text = "Height") })
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Дневная активность", style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(800),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp
                ), color = MaterialTheme.colorScheme.primary
            )
            Slider(
                value = dailyActive,
                onValueChange = { dailyActive = it },
                modifier = Modifier.width(300.dp)
            )
            Text(text = dailyActive.toString())
            Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.End)) {
                Text(text = "Сохранить")
            }
        }
    }
    if (isVisible)
        AlertDialog(onDismissRequest = { isVisible = false }) {
            Surface {
                Column {
                    DatePicker(state = datePickerState)
                    Button(onClick = {
                        if (datePickerState.selectedDateMillis != null) {
                            date.value =
                                SimpleDateFormat("MM/dd/yyyy").format(Date(datePickerState.selectedDateMillis!!))
                            isVisible = false
                        }
                    }) {
                        Text(text = "Ok!")
                    }
                }
            }

        }
}


@Preview
@Composable
fun AccountScreenPrev() {
    FitnessAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AccountScreen()
        }
    }
    
}