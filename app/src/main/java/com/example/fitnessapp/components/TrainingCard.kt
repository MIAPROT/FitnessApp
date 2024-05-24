package com.example.fitnessapp.components

import TimerBar
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.fitnessapp.models.TrainingCardDTO

@Composable
fun TrainingCard(trainingInfo: TrainingCardDTO) {
    val webView = WebView(LocalContext.current).apply {
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        webViewClient = WebViewClient()
    }
    var timerVisible by remember {
        mutableStateOf(false)
    }
    val htmlData = getHTMLData(trainingInfo.link) //wEX1_NYoPls
    Card(
        Modifier
            .padding(end = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                RoundedCornerShape(12.dp)
            )
    ) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AndroidView(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), factory = { webView }) { view ->
                view.loadDataWithBaseURL(
                    "https://www.youtube.com",
                    htmlData,
                    "text/html",
                    "UTF-8",
                    null
                )
            }
            if (!timerVisible) {
                Text(
                    text = trainingInfo.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = trainingInfo.description,
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Button(onClick = { timerVisible = !timerVisible}) {
                    Text(text = "Понятно")
                    
                }
            }
            else
            {
                TimerBar(timerdelay = trainingInfo.timer)
            }
        }
    }


}

