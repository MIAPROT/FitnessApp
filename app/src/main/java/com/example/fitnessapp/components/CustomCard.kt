package com.example.fitnessapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.fitnessapp.db.Db
import com.example.fitnessapp.db.MuscularType
import com.example.fitnessapp.models.TrainingCardDTO
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import org.jetbrains.exposed.sql.transactions.transaction

@Composable
fun CustomCard(card: TrainingCardDTO, modifier: Modifier) {
    Db
    var textDescription by remember { mutableStateOf("") }

    Card(
        modifier
            .padding(end = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                RoundedCornerShape(12.dp)
            )
    ) {
        Row {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                SubcomposeAsyncImage(
                    model = card.image,
                    loading = {
                        CircularProgressIndicator()
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .border(
                            BorderStroke(0.dp, MaterialTheme.colorScheme.outline),
                            RoundedCornerShape(8.dp)
                        ),
                    contentScale = ContentScale.FillWidth,
                )
                Text(text = card.name, style = MaterialTheme.typography.titleMedium)
                if (card.showDate) {
                    Text(
                        text = card.date.toString(),
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.labelSmall
                    )
                } else {
                    Text(
                        text = card.description,
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                transaction {
                    textDescription = MuscularType.findById(card.muscularType)!!.name
                }
                Text(
                    text = textDescription, style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(800),
                        letterSpacing = 0.1.sp
                    ), color = MaterialTheme.colorScheme.primary
                )
            }


        }
    }
}

@Composable
@Preview
fun CardPreview() {
    FitnessAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            CustomCard(
                TrainingCardDTO(
                    "Тест2",
                    "Тест",
                    "https://webpulse.imgsmail.ru/imgpreview?mb=webpulse&key=pulse_cabinet-image-6ed916c1-76f0-4e4a-8b29-f4a1742651e6",
                    false,
                    destination = "",
                    timer = 10,
                    muscularType = 1,
                    link = "",
                    id = 1
                ), Modifier
            )
        }
    }
}