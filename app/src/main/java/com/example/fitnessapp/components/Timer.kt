import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun TimerBar(timerdelay: Int) {
    var ticks by remember { mutableStateOf(0) }
    var timergo by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(timergo) {
        while (timergo) {
            delay(1.seconds)
            ticks++
            if(ticks == timerdelay)
            {
                timergo = false
                ticks = 0
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = { ticks.toFloat() / timerdelay },
                modifier = Modifier
                    .size(100.dp)
                    .padding(4.dp),
                strokeWidth = 10.dp,
            )
            Text(
                text = "$ticks", style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(800),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp
                )
            )
        }
        Button(onClick = { timergo = !timergo }) {
            Text(
                text = if (timergo) {
                    "Стоп"
                } else if (ticks == 0) {
                    "Старт"
                } else "Продолжить"
            )
        }
    }
}


@Preview
@Composable
fun TimerBarPreview() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        TimerBar(10)
    }

}
