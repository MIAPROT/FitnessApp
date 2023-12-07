package com.example.fitnessapp.components

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingCard() {
    val webView = WebView(LocalContext.current).apply {
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        webViewClient = WebViewClient()
    }
    val htmlData = getHTMLData("wEX1_NYoPls")
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

        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            AndroidView(factory = { webView }){ view ->
                view.loadDataWithBaseURL(
                    "https://www.youtube.com",
                    htmlData,
                    "text/html",
                    "UTF-8",
                    null
                )
            }
            Text(text = "Название", style = MaterialTheme.typography.titleMedium)
            Text(
                text = "Описание",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }


}

@Preview
@Composable
fun TrainingCardScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        TrainingCard()
    }

}
