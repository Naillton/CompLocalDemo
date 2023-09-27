package com.nailton.complocaldemo

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nailton.complocaldemo.ui.theme.CompLocalDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompLocalDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Composable1()
                }
            }
        }
    }
}

/*
    criando compositionLocal que sera usado para prover cores para determiandos elementos
    na hierarquia de composicoes, o staticCompositionLocalOf é usado quando necessitamos
    que o estado guardado não seja modificado constantemente, já o compositionLocalOf sera
    usado quando tivermos dados que serão recomposto constantemente
*/
val LocalColor = staticCompositionLocalOf { Color.Cyan }

@Composable
private fun Composable1() {
    val color = if(isSystemInDarkTheme()) {
        Color.Cyan
    } else {
        Color.Yellow
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Composable2()
        CompositionLocalProvider(LocalColor provides color) {
            Composable3()
        }
    }
}

@Composable
private fun Composable2() {
    Composable4()
}

@Composable
private fun Composable3() {
    Text("Composable3", modifier = Modifier.background(LocalColor.current))
    CompositionLocalProvider(LocalColor provides Color.Red) {
        Composable5()
    }
}

@Composable
private fun Composable4() {
    Composable6()
}

@Composable
private fun Composable5() {
    Text("Composable 5", modifier = Modifier.background(LocalColor.current))
    CompositionLocalProvider(LocalColor provides Color.Green) {
        Composable7()
    }
    CompositionLocalProvider(LocalColor provides Color.Yellow) {
        Composable8()
    }
}

@Composable
private fun Composable6() {
    Text("Composable 6")
}

@Composable
private fun Composable7() {
    Text("Composable 7", modifier = Modifier.background(LocalColor.current))
}

@Composable
private fun Composable8() {
    Text(
        text = "Composable 8",
        modifier = Modifier
            .padding(10.dp)
            .background(LocalColor.current)
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompLocalDemoTheme {
        Composable1()
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DarkPreview() {
    CompLocalDemoTheme {
        Composable1()
    }
}