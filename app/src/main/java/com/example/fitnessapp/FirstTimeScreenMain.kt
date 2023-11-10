package com.example.fitnessapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import java.text.SimpleDateFormat
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstTimeScreenMain(navController: NavHostController) {
    var currentScreenId by remember {
        mutableIntStateOf(0)
    }
    var weight = remember {
        mutableStateOf("")
    }
    var height = remember {
        mutableStateOf("")
    }
    var date = remember {
        mutableStateOf("")
    }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)
    var isVisible by remember {
        mutableStateOf(false)
    }
    var dailyActive by remember { mutableStateOf(0f) }
    var screenList = listOf<@Composable () -> Unit>({
        Text(
            text = "Приложение для фитнеса", style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(800),
                textAlign = TextAlign.Center,
                letterSpacing = 0.1.sp
            ), color = MaterialTheme.colorScheme.primary
        )
    },
        {
            Text(
                text = "Для начала тренировки мы должны узнать о вас побольше <3",
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(800),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp
                ),
                color = MaterialTheme.colorScheme.primary
            )
        },
        {
            Column() {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Ваша дата рождения?", style = TextStyle(
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
            }
        },
        {
            Column() {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Ваш вес?", style = TextStyle(
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
            }

        },
        {
            Column() {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Ваш рост?", style = TextStyle(
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
            }
        },
        {
            Column() {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(300.dp),
                    text = "Измерьте свою дневную активность", style = TextStyle(
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
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(300.dp),
                    text = "TestText", style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(800),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.1.sp
                    ), color = MaterialTheme.colorScheme.primary
                )
            }
        },
        {
            Column() {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(300.dp),
                    text = "Готово!", style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(800),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.1.sp
                    ), color = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(300.dp),
                    text = "Свои параметры вы можете изменить в личной информации",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(800),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.1.sp
                    ),
                    color = MaterialTheme.colorScheme.primary
                )

            }
        })
    Box(Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(Alignment.Center)) {
            screenList[currentScreenId]()
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
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            shape = RectangleShape,
            onClick = {
                if (currentScreenId == screenList.size - 1) {
                    navController.navigate("MainScreen")
                } else {
                    currentScreenId++
                }
            }) {
            Text(text = if (currentScreenId == 0 || currentScreenId == screenList.size - 1) "НАЧАТЬ ТРЕНИРОВКУ" else "ДАЛЕЕ")
        }

    }
}

@Preview
@Composable
fun FirstTimeScreenMainPreview() {
    FitnessAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            FirstTimeScreenMain(rememberNavController())
        }
    }

}