package com.example.fitnessapp

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import com.example.fitnessapp.db.Db
import com.example.fitnessapp.db.Person
import com.example.fitnessapp.db.Persons.height
import com.example.fitnessapp.db.Persons.weight
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import org.jetbrains.exposed.sql.not
import org.jetbrains.exposed.sql.transactions.transaction
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import kotlin.time.Duration

@SuppressLint("ScheduleExactAlarm")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen() {
    Db

    var isNotificationVisible by remember {
        mutableStateOf(false)
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

    var notification_date: Instant = remember {
        Instant.fromEpochMilliseconds(0)
    }
    var isDatePickerVisible by remember {
        mutableStateOf(false)
    }
    var isDatePickerNotificationVisible by remember {
        mutableStateOf(false)
    }
    var isCalculatorVisible by remember {
        mutableStateOf(false)
    }
    var message = remember {
        mutableStateOf("")
    }
    var isEditing by remember { mutableStateOf(false) }
    var dailyActive by remember { mutableStateOf(0f) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    )

    { contentPadding ->

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding), contentAlignment = Alignment.Center)
        {
            Column(modifier = Modifier.verticalScroll(scrollState)) {
                LaunchedEffect(null ){
                    transaction { weight.value = Person.findById(1)!!.weight.toString()
                     height.value = Person.findById(1)!!.height.toString()
                     dailyActive = Person.findById(1)!!.activity.toFloat() }

                }
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
                        IconButton(onClick = { isDatePickerVisible = true }) {
                            Icon(Icons.Default.DateRange, contentDescription = null)
                        }
                    },
                    label = { Text(text = "Date") },enabled = isEditing)
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
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    label = { Text(text = "Weight") },enabled = isEditing)
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
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    label = { Text(text = "Height") },enabled = isEditing)
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
                    valueRange = 1000f..1900.0f,
                    modifier = Modifier.width(300.dp),enabled = isEditing
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(300.dp),
                    text = if (dailyActive <= 1200) {
                        "Для малоподвижных людей, тренировок мало или они отсутствуют"
                    } else if (dailyActive <= 1375) {
                        "Для людей с низкой активностью, легкие тренировки 1-3 раза в неделю или в виде эквивалента другой активности."
                    } else if(dailyActive <= 1550){
                        "Для умеренно активных людей: физическая работа средней тяжести или регулярные тренировки 3-5 дней в неделю."
                    } else if(dailyActive <= 1725){
                        "Для очень активных людей: физическая работа полный день или интенсивные тренировки 6-7 раз в неделю."
                    } else{
                        "Для предельно активных людей: тяжелая физическая работа и интенсивные тренировки/занятия спортом."
                    }, style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(800),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.1.sp
                    ), color = MaterialTheme.colorScheme.primary
                )
                Text(text = (dailyActive).toString(), style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(800),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp
                ), color = MaterialTheme.colorScheme.primary)
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedButton(onClick = { isEditing = !isEditing }) {
                    if(isEditing){Text(text = "Отменить")}else{Text(text = "Изменить")}
                    }
                    Button(onClick = { scope.launch {
                        snackbarHostState.showSnackbar("Данные сохранены!")
                    }
                    transaction { Person.findById(1)!!.weight = weight.value.toInt()
                        Person.findById(1)!!.height = height.value.toInt()
                        Person.findById(1)!!.activity = dailyActive.toInt()
                        println(Person.findById(1)!!.activity.toString())
                        println(((((Person.findById(1)!!.weight)*10)+((Person.findById(1)!!.height)*6.25)-(5*(Person.findById(1)!!.age))+5)*(Person.findById(1)!!.activity/1000)).toInt())}}) {
                        Text(text = "Сохранить")
                    }
                    IconButton(onClick = { isCalculatorVisible = true }) {
                        Icon(Icons.Default.Face, contentDescription = null)
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = { isNotificationVisible = true}) {
                        Text(text = "Нопоминания")
                    }
                }

            }
        }
        if (isDatePickerVisible)
            AlertDialog(onDismissRequest = { isDatePickerVisible = false }) {
                Surface {
                    Column {
                        DatePicker(state = datePickerState)
                        Button(onClick = {
                            if (datePickerState.selectedDateMillis != null) {
                                val selectedDate = Calendar.getInstance().apply {
                                    timeInMillis = datePickerState.selectedDateMillis!!
                                }
                                val currentDate = Calendar.getInstance()

                                var age = currentDate.get(Calendar.YEAR) - selectedDate.get(Calendar.YEAR)

                                // Добавьте проверку для случая, если день рождения в этом году еще не наступил
                                if (currentDate.get(Calendar.DAY_OF_YEAR) < selectedDate.get(
                                        Calendar.DAY_OF_YEAR)) {
                                    age--
                                }

                                // Проверьте, что возраст представляет собой непустую числовую строку перед попыткой преобразования
                                val ageString = age.toString()
                                if (ageString.isNotBlank()) {
                                    println(age)
                                    transaction {
                                        Person.findById(1)!!.age = age
                                    }
                                    date.value = SimpleDateFormat("MM/dd/yyyy").format(Date(datePickerState.selectedDateMillis!!))
                                    isDatePickerVisible = false
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

        if (isNotificationVisible) {
            Dialog(onDismissRequest = { isNotificationVisible = false }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(600.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(Modifier.padding(16.dp)) {
                        OutlinedTextField(
                            value = notification_date.toString(),
                            onValueChange = {  },
                            trailingIcon = {
                                IconButton(onClick = { isDatePickerNotificationVisible = true }) {
                                    Icon(Icons.Default.DateRange, contentDescription = null)
                                }
                            },
                            label = { Text(text = "Date") })
                        val timePickerStateHorizontal = rememberTimePickerState()
                        TimePicker(
                            state = timePickerStateHorizontal,
                            layoutType = TimePickerLayoutType.Vertical
                        )
                        OutlinedTextField(
                            value = message.value,
                            onValueChange = { newText -> message.value = newText },
                            label = { Text(text = "Текст") }, modifier = Modifier.padding(bottom = 8.dp))



                        val context = LocalContext.current
                        Button(onClick = {
                            @SuppressLint("ScheduleExactAlarm")
                            val intent = Intent(context, Notification:: class.java)
                            intent.putExtra(titleExtra, "Уведомление")
                            intent.putExtra(messageExtra,message.value)
                            val pendingIntent = PendingIntent.getBroadcast(
                                context,
                                notificationID,
                                intent,
                                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                            )
                            val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager

                            notification_date = notification_date + Duration.parse("${timePickerStateHorizontal.hour}h ${timePickerStateHorizontal.minute}m")


                            // Get the selected time and schedule the notification
                            alarmManager.setExactAndAllowWhileIdle(
                                AlarmManager.RTC_WAKEUP,
                                notification_date.toEpochMilliseconds(),
                                pendingIntent
                            )
                        }) {
                            Text(text = "Отправить")
                        }
                        Button(onClick = {
                            isNotificationVisible = false

                        }){
                            Text(text = "Выйти")
                        }


                    }
                }
            }
        }
        if (isDatePickerNotificationVisible)
            AlertDialog(onDismissRequest = { isDatePickerNotificationVisible = false }) {
                Surface {
                    Column {
                        DatePicker(state = datePickerState)
                        Button(onClick = {
                            if (datePickerState.selectedDateMillis != null) {
                                notification_date = Instant.fromEpochMilliseconds(datePickerState.selectedDateMillis!!)

                                isDatePickerNotificationVisible = false
                            }
                        }) {
                            Text(text = "Ok!")
                        }
                    }
                }

            }



        if (isCalculatorVisible)
            AlertDialog(onDismissRequest = { isCalculatorVisible = false }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(400.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(Modifier.padding(16.dp)) {
                        //elements for notifications
                        CalculatorScreen()
                        Button(onClick = {
                            isCalculatorVisible = false
                        }) {
                            Text(text = "Ok")
                        }
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