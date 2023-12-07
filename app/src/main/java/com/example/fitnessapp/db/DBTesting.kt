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
            // print sql to std-out

            SchemaUtils.create (IdividualExcercises)

            IdividualExcercise.new{
                name = "Подъём корпуса на прес"
                muscular_id = 1
                link = "o0T9qoofUNY"
                timer = "0:00:35"
            }




            // 'select *' SQL: SELECT Cities.id, Cities.name FROM Cities
            println("ReadyMadeWorkouts: ${IdividualExcercise.all().forEach{ println(it.name + " " + it.link) }}")

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