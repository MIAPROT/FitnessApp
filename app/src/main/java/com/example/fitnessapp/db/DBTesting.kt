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
            Muscular_Type.new { name = "грудь" }
            Muscular_Type.new { name = "бицепс" }
            Muscular_Type.new { name = "трицепс" }
            Muscular_Type.new { name = "плечи" }
            Muscular_Type.new { name = "спина" }
            Muscular_Type.new { name = "квадрицепс" }
            Muscular_Type.new { name = "икроножные" }
            Muscular_Type.new { name = "ягодичные" }

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
                    link = "FWk-bfKceEU"
                    timer = 30
                    image = "https://sportishka.com/uploads/posts/2022-11/1667570993_34-sportishka-com-p-press-podem-tulovishcha-oboi-37.jpg"
                    description = "Лягте на спину, согните ноги в коленях под углом 90 градусов, руки за головой, локти разведены в стороны \n" +
                            "Округлите спину и медленно усилием мышц живота поднимайте плечевой пояс по направлению к тазу. \n" +
                            "Старайтесь максимально напрячь мышцы пресса."
                }
                IndividualExcercise.new {
                    name = "Планка на пресс"
                    muscular_id = 2
                    link = "FjgCni0fhUA"
                    timer = 30
                    image = "https://shenny.ru/wp-content/uploads/2021/11/planka.jpg"
                    description = "Займите исходное положение для отжиманий.\n" +
                            "Согните локти и расположите предплечья на полу под прямым углом к плечевым костям.\n" +
                            "Следите, чтобы тело было прямым: не прогибайтесь и не поднимайте таз.\n" +
                            "Держите ровную линию тела с помощью напряжения пресса и слегка подкручивайте таз к животу."
                }

                IndividualExcercise.new {
                    name = "Классические отжимания"
                    muscular_id = 3
                    link = "c902b5g8w0A"
                    timer = 120
                    image = "https://fitnessclub-beauty.ru/wp-content/uploads/2/e/1/2e136af36ca55150c5babd42be02b937.jpeg"
                    description = "Исходное положение — принять упор лежа, опираясь на ладони и носки.\n" +
                            "Расставить руки на ширину плеч, выпрямить спину.\n" +
                            "На вдохе опустить тело вниз, сгибая руки в локтях, так, чтобы между полом и корпусом осталось около 3-5 сантиметров.\n" +
                            "На выдохе плавно подняться и повторить."
                }

                IndividualExcercise.new {
                    name = "Подъем на бицепс штанги/гантелей стоя"
                    muscular_id = 4
                    link = "3epc8Xb_lwg"
                    timer = 120
                    image = "https://cross.expert/wp-content/uploads/2018/03/uprazhneniya-na-bitseps-podem-shtangi.jpeg"
                    description = "1.Взять снаряд. Штангу можно делать с прямым или изогнутым грифом. Разница лишь в удобстве для ваших кистей. Хват – на ширине плеч или чуть уже. \n" +
                            " Если не поворачивать гантель, а продолжать поднимать ее без супинации, получится упражнение в молотковом стиле. Оно хорошо развивает брахиалис и мышцы предплечья.\n" +
                            "2.Медленно поднять снаряд до пикового состояния, без рывков и движений спины. Локти старайтесь не выводить вперед.\n" +
                            "3.Удержать его в этом состоянии на 2-3 секунды.\n" +
                            "4.Максимально медленно опустить его вниз, не полностью разгибая руки в локтях."
                }
                IndividualExcercise.new {
                    name = "Обратные отжимания"
                    muscular_id = 5
                    link = "olH5e5UnUo8"
                    timer = 120
                    image = "https://crossi.ru/wp-content/uploads/0/a/4/0a47d18d5e8b17d825dbee7c628e11cd.png"
                    description = "Расположите руки на ширине плеч на спортивной скамье или устойчивом стуле. Подвиньтесь поближе к скамье, локти должны находиться как можно ближе к телу. Вытяните ноги в длину перед собой, немного согните в коленях, пятки упираются в пол.\n" +
                            "На вдохе медленно согните локти, чтобы опустить свое тело к полу, пока плечо и предплечье не образуют угол 90 градусов. Cпина прямая. Задержитесь на 3-5 секунд.\n" +
                            "На выдохе, оттолкнувшись от скамьи, выпрямите локти и вернитесь в исходное положение.\n" +
                            "Не сутультесь. старайтесь опускать плечи, не подтягивайте их к ушам, когда опускаете и поднимаете тело."
                }
                IndividualExcercise.new {
                    name = "Подъёмы (махи) перед собой"
                    muscular_id = 6
                    link = "1bTI7uhoWmw"
                    timer = 120
                    image = "https://gbuenergiya.ru/wp-content/uploads/2/f/1/2f15a5f4ce581ff61f425616c8ef3e50.jpeg"
                    description = "1.ИП – стоя, ноги на ширине плеч, руки с гантелями опущены вниз и расположены перед бёдрами, хват прямой.\n" +
                            "2.Без рывка и инерции поднимите руки перед собой, зафиксировав их на мгновение на уровне плеч. Выше поднимать не нужно – нагрузка с дельт уходит на трапецию.\n" +
                            "3.Медленно верните руки в ИП."
                }
                IndividualExcercise.new {
                    name = "Подтягивания"
                    muscular_id = 7
                    link = "eGo4IYlbE5g"
                    timer = 120
                    image = "https://cross.expert/wp-content/uploads/2018/03/uprazhneniya-na-bitseps-podem-shtangi.jpeg"
                    description = "Запрыгните и ухватитесь за перекладину широким хватом. Зафиксируйте туловище, не раскачивайтесь. Вдохните.\n" +
                            "На выдохе отведите назад и опустите лопатки, одновременно сгибайте обе руки и тянитесь к перекладине, локти направьте вниз.\n" +
                            "Выполняйте движение с полной амплитудой — в верхней точке подбородок должен быть над уровнем перекладины.\n" +
                            "Избегайте рывков при подъеме и обрывов при обратном движении, выполняйте упражнение плавно.\n" +
                            "Выпрямите руки и вернитесь в исходное положение."
                }
                IndividualExcercise.new {
                    name = "Фронтальные приседания со штангой"
                    muscular_id = 8
                    link = "2FpY2DFvoQw"
                    timer = 120
                    image = "https://cross.expert/wp-content/uploads/2018/04/uprazhneniya-na-kvadritseps-frontalnyi.jpeg"
                    description = "Подойдите к грифу, лежащему на стойках, и зафиксируйте его на передних дельтах. Руки располагаются крест-накрест, помогая удерживать штангу – это ИП.\n" +
                            "Держа спину абсолютно ровной, присядьте до параллели.\n" +
                            "Вернитесь в ИП."
                }
            }
            ReadyMadeWorkout.new(){
                name = "Тренировка на пресс"
                description = "Тренировка на прес дома\nЛёгкая тренировка при помощи подручных средств\n10 упражнений\n10 минут"
                image = "https://sportishka.com/uploads/posts/2022-11/1667517758_12-sportishka-com-p-podem-korpusa-na-press-vkontakte-12.jpg"
                readyMadeWorkouts = SizedCollection(listOf(IndividualExcercise.findById(1)!!,IndividualExcercise.findById(2)!!))
            }
            ReadyMadeWorkout.new(){
                name = "Тренировка на верхнюю часть тела"
                description = "Тренировка на верхнюю часть тела\nЛёгкая тренировка при помощи подручных средств\n6 упражнений\n15 минут"
                image = "https://avatars.dzeninfra.ru/get-zen_doc/4715514/pub_602cbbef2ca49f594850afa1_602ccbccaca27a71a62c7552/scale_1200"
                readyMadeWorkouts = SizedCollection(listOf(IndividualExcercise.findById(1)!!,IndividualExcercise.findById(3)!!,IndividualExcercise.findById(4)!!,IndividualExcercise.findById(5)!!,IndividualExcercise.findById(6)!!,IndividualExcercise.findById(7)!!))
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