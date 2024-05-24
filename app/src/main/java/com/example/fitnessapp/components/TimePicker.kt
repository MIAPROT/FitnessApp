package com.example.fitnessapp.components
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun TimePicker(context: Context) {
    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    val time = remember { mutableStateOf("")}
    val timePickerDialog = TimePickerDialog(
        context,
        {_, hour: Int, minute: Int ->
            time.value = "$hour:$minute"
        }, hour, minute, false
        )

    OutlinedTextField(
        value = time.value,
        onValueChange = { newText -> time.value = newText },
        trailingIcon = {
            IconButton(onClick = { timePickerDialog.show() }) {
                Icon(Icons.Default.DateRange, contentDescription = null)
            }
        },
        label = { Text(text = "Time") })
}