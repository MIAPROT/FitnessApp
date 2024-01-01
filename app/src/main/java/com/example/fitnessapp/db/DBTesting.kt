package com.example.fitnessapp.db

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp.IndividualExercises
import com.example.fitnessapp.db.IdividualExcercises.timer
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toLocalTime
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

@Composable
fun DBTesting() {
    LaunchedEffect(null ){
        Db
        transaction {

            SchemaUtils.create (Muscular_Types, IdividualExcercises, ReadyMadeWorkouts, ReadyMadeWorkouts_Idividual_Exercises, DoneExcercises)
            Muscular_Type.new { name="Грудь" }
            IdividualExcercise.new{
                name = "Подъём корпуса на прес"
                muscular_id = 1
                link = "o0T9qoofUNY"
                timer = "0:00:35"
            }

            println("IdividualExcercise: ${IdividualExcercise.all().forEach{ println(it.name + " " + it.link) }}")
            println("Muscular_types: ${Muscular_Type.all().forEach{ println(it.name) }}")

        }
    }
}

@Preview
@Composable
fun DBTestingScreen() {
    FitnessAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            DBTesting()
        }
    }

}