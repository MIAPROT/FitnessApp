package com.example.fitnessapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.components.DialogCard
import com.example.fitnessapp.db.Db
import com.example.fitnessapp.db.Person
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import org.jetbrains.exposed.sql.transactions.transaction

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CalculatorScreen() {
    Db
    var OpenAlertDialogInfo by remember {
        mutableStateOf(false)
    }
    var OpenAlertDialogLightWeight by remember {
        mutableStateOf(false)
    }
    var OpenAlertDialogHardWeight by remember {
        mutableStateOf(false)
    }
    var OpenAlertDialogNormalWeight by remember {
        mutableStateOf(false)
    }

    if (OpenAlertDialogInfo) {
        AlertDialog(onDismissRequest = { OpenAlertDialogInfo = false }) {
            DialogCard(
                mainText = "Калькулятор калорий определяет количество энергии, которое вам следует получать ежедневно, чтобы достичь ваших целей.",
                secondaryText = "Нажмайте на иконки за доп. информацией",
                onClick = { OpenAlertDialogInfo = false }
            )

        }
    }
    if (OpenAlertDialogLightWeight) {
        AlertDialog(onDismissRequest = { OpenAlertDialogLightWeight = false }) {
            DialogCard(
                mainText = "Количество калорий необходимое для похудения",
                secondaryText = "",
                onClick = { OpenAlertDialogLightWeight = false }
            )

        }
    }
    if (OpenAlertDialogHardWeight) {
        AlertDialog(onDismissRequest = { OpenAlertDialogHardWeight = false }) {
            DialogCard(
                mainText = "Количество калорий необходимое для набора массы",
                secondaryText = "",
                onClick = { OpenAlertDialogHardWeight = false }
            )

        }
    }
    if (OpenAlertDialogNormalWeight) {
        AlertDialog(onDismissRequest = { OpenAlertDialogNormalWeight = false }) {
            DialogCard(
                mainText = "Количество калорий необходимое для поддержания веса",
                secondaryText = "",
                onClick = { OpenAlertDialogNormalWeight = false }
            )

        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Калькулятор калорий", style = MaterialTheme.typography.titleLarge
            )
            IconButton(onClick = { OpenAlertDialogInfo = true }) {
                Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(16.dp))
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            var NormalKcal by remember { mutableStateOf(0) }
            transaction {
                NormalKcal =
                    ((((Person.findById(1)!!.weight) * 10) + ((Person.findById(1)!!.height) * 6.25) - (5 * (Person.findById(
                        1
                    )!!.age)) + 5) * (Person.findById(1)!!.activity / 1000)).toInt()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                IconButton(onClick = { OpenAlertDialogLightWeight = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.lightweightbaby),
                        contentDescription = null, modifier = Modifier.size(48.dp)
                    )
                }
                Text(
                    text = (NormalKcal - 400).toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = "калорий", style = MaterialTheme.typography.labelLarge
                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                IconButton(onClick = { OpenAlertDialogHardWeight = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.hardweightbaby),
                        contentDescription = null, modifier = Modifier.size(48.dp)
                    )
                }
                Text(
                    text = (NormalKcal + 500).toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = "калорий", style = MaterialTheme.typography.labelLarge
                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                IconButton(onClick = { OpenAlertDialogNormalWeight = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.normalweightbaby),
                        contentDescription = null, modifier = Modifier.size(48.dp)
                    )
                }
                Text(
                    text = NormalKcal.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = "калорий", style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview()
@Composable
fun CalculatorScreenPreview() {
    FitnessAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            CalculatorScreen()
        }
    }

}