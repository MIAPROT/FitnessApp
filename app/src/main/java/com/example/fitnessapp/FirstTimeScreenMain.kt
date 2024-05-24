package com.example.fitnessapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.db.DBTesting
import com.example.fitnessapp.db.Db
import com.example.fitnessapp.db.Person
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import org.jetbrains.exposed.sql.transactions.transaction
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstTimeScreenMain(navController: NavHostController) {
    Db
    DBTesting()

    var currentScreenId by remember {
        mutableIntStateOf(0)
    }
    val weight = remember {
        mutableStateOf("")
    }
    val height = remember {
        mutableStateOf("")
    }
    val date = remember {
        mutableStateOf("")
    }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)
    var isVisible by remember {
        mutableStateOf(false)
    }
    var dailyActive by remember { mutableStateOf(0f) }
    val screenList = listOf<@Composable () -> Unit>({
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
            Column {
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
                    label = { Text(text = "Дата рождения") })
            }
        },
        {
            Column {
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
                    onValueChange = { newText ->
                        // Check if the input is a valid integer before updating the value
                        if (newText.isDigitsOnly()) {
                            weight.value = newText
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    trailingIcon = {
                        Image(
                            Icons.Default.Close, contentDescription = null
                        )
                    },
                    label = { Text(text = "Вес") }
                )
            }


        },
        {
            Column {
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
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    label = { Text(text = "Рост") })
                transaction {
                    Person.findById(1)!!.weight = weight.value.toInt()
                }
            }
        },
        {
            Column {
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
                    valueRange = 1000f..1900.0f,
                    modifier = Modifier.width(300.dp)
                )
                Text(
                    text = dailyActive.toString(), style = TextStyle(
                        fontSize = 12.sp,
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
                    text = if (dailyActive <= 1200) {
                        "Для малоподвижных людей, тренировок мало или они отсутствуют"
                    } else if (dailyActive <= 1375) {
                        "Для людей с низкой активностью, легкие тренировки 1-3 раза в неделю или в виде эквивалента другой активности."
                    } else if (dailyActive <= 1550) {
                        "Для умеренно активных людей: физическая работа средней тяжести или регулярные тренировки 3-5 дней в неделю."
                    } else if (dailyActive <= 1725) {
                        "Для очень активных людей: физическая работа полный день или интенсивные тренировки 6-7 раз в неделю."
                    } else {
                        "Для предельно активных людей: тяжелая физическая работа и интенсивные тренировки/занятия спортом."
                    }, style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(800),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.1.sp
                    ), color = MaterialTheme.colorScheme.primary
                )
                transaction {
                    Person.findById(1)!!.height = height.value.toInt()
                }
            }
        },
        {
            Column {
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
                    text = "Свои параметры вы можете изменить в профиле",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(800),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.1.sp
                    ),
                    color = MaterialTheme.colorScheme.primary
                )
                transaction {
                    Person.findById(1)!!.activity = dailyActive.toInt()
                }

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
                                val selectedDate = Calendar.getInstance().apply {
                                    timeInMillis = datePickerState.selectedDateMillis!!
                                }
                                val currentDate = Calendar.getInstance()

                                var age =
                                    currentDate.get(Calendar.YEAR) - selectedDate.get(Calendar.YEAR)

                                // Добавьте проверку для случая, если день рождения в этом году еще не наступил
                                if (currentDate.get(Calendar.DAY_OF_YEAR) < selectedDate.get(
                                        Calendar.DAY_OF_YEAR
                                    )
                                ) {
                                    age--
                                }

                                // Проверьте, что возраст представляет собой непустую числовую строку перед попыткой преобразования
                                val ageString = age.toString()
                                if (ageString.isNotBlank()) {
                                    println(age)
                                    transaction {
                                        Person.findById(1)!!.age = age
                                    }
                                    date.value =
                                        SimpleDateFormat("MM/dd/yyyy", Locale.ROOT).format(Date(datePickerState.selectedDateMillis!!))
                                    isVisible = false
                                } else {
                                    println("Недопустимый возраст: $ageString")
                                }
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