package com.example.lemonadestand

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeApp()
        }
    }
}

@Composable
fun LemonadeApp() {
    ScreenUpdate(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun ScreenUpdate(modifier: Modifier = Modifier) {

    var click by remember { mutableIntStateOf(1) }
    val imageResource = when (click) {
        1 -> R.drawable.lemon_tree
        in 2..7 -> R.drawable.lemon_squeeze
        in 8..12 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val textResource = when (click) {
        1 -> R.string.tree_tap
        in 2..7 -> R.string.lemon_tap
        in 8..10 -> R.string.ade_tap
        else -> R.string.empty_tap
    }

    Column(
        modifier =   modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            when (imageResource) {
                R.drawable.lemon_tree -> click++
                R.drawable.lemon_squeeze -> click += (1..5).random()
                R.drawable.lemon_drink -> click = 13
                else -> click = 1
            }
        }) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = imageResource.toString()
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = stringResource(textResource))
    }
}

@Preview
@Composable
fun LAdeAppPreview(modifier: Modifier = Modifier) {
    LemonadeApp()
}