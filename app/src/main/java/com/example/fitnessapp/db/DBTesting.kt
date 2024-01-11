package com.example.fitnessapp.db

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.transactions.transaction

@Composable
fun DBTesting() {
    LaunchedEffect(null) {
        Db
        transaction {

            SchemaUtils.create(
                Muscular_Types,
                IndividualExcercises,
                ReadyMadeWorkouts,
                ReadyMadeWorkouts_Idividual_Exercises,
                DoneExcercises,
                Persons
            )
            Muscular_Type.new { name = "" }
            Muscular_Type.new { name = "пресс" }
            Person.new{
                age = 30
                weight = 60
                height = 178
                activity = 1500
            }


            println("Muscular_types: ${Muscular_Type.all().forEach { println(it.name) }}")

        }

        transaction {
            transaction {

                IndividualExcercise.new {
                    name = "Подъём корпуса на пресс"
                    muscular_id = 2
                    link = "o0T9qoofUNY"
                    timer = 30
                    image = "https://shenny.ru/wp-content/uploads/2021/11/planka.jpg"
                    description = "Лягте на спину, согните ноги в коленях под углом 90 градусов, руки за головой, локти разведены в стороны \n" +
                            "Округлите спину и медленно усилием мышц живота поднимайте плечевой пояс по направлению к тазу. \n" +
                            "Старайтесь максимально напрячь мышцы пресса."
                }
                IndividualExcercise.new {
                    name = "Планка на пресс"
                    muscular_id = 2
                    link = "o0T9qoofUNY"
                    timer = 30
                    image = "https://shenny.ru/wp-content/uploads/2021/11/planka.jpg"
                    description = "Займите исходное положение для отжиманий.\n" +
                            "Согните локти и расположите предплечья на полу под прямым углом к плечевым костям.\n" +
                            "Следите, чтобы тело было прямым: не прогибайтесь и не поднимайте таз.\n" +
                            "Держите ровную линию тела с помощью напряжения пресса и слегка подкручивайте таз к животу."
                }
            }
            ReadyMadeWorkout.new(){
                name = "Тренировка на пресс"
                description = "Тренировка на прес дома\nЛёгкая тренировка при помощь подручных средств\n10 упражнений\n15 минут"
                image = "https://sportishka.com/uploads/posts/2022-11/1667517758_12-sportishka-com-p-podem-korpusa-na-press-vkontakte-12.jpg"
                readyMadeWorkouts = SizedCollection(IndividualExcercise.all().toList())
            }
            DoneExcercise.new{
                individualExcercises = IndividualExcercise.findById(1)!!.id
                readyMadeWorkouts = null
            }
            DoneExcercise.new{
                individualExcercises = null
                readyMadeWorkouts = ReadyMadeWorkout.findById(1)!!.id
            }

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