package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtApp()
                }
            }
        }
    }
}

@Composable
fun ArtApp(){
    var currentStep by remember { mutableStateOf(1) }

    when (currentStep) {
        1 -> ArtSpaceGallery(
                drawableResourceId = R.drawable.sydney,
                titleTextId = R.string.title_1,
                authorTextId = R.string.author_1,
                onNextClick = {
                    currentStep = 2
                },
                onPreviousClick = {
                    currentStep = 4
                }
            )
        2 -> ArtSpaceGallery(
            drawableResourceId = R.drawable.uluru,
            titleTextId = R.string.title_2,
            authorTextId = R.string.author_2,
            onNextClick = {
                currentStep = 3
            },
            onPreviousClick = {
                currentStep = 1
            }
        )
        3 -> ArtSpaceGallery(
            drawableResourceId = R.drawable.kangaroo,
            titleTextId = R.string.title_3,
            authorTextId = R.string.author_3,
            onNextClick = {
                currentStep = 4
            },
            onPreviousClick = {
                currentStep = 2
            }
        )
        4 -> ArtSpaceGallery(
            drawableResourceId = R.drawable.sea_turtle,
            titleTextId = R.string.title_4,
            authorTextId = R.string.author_4,
            onNextClick = {
                currentStep = 1
            },
            onPreviousClick = {
                currentStep = 3
            }
        )
    }
}

@Composable
fun ArtSpaceGallery(
    drawableResourceId: Int,
    titleTextId: Int,
    authorTextId: Int,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image = painterResource(drawableResourceId)
        Spacer(modifier = Modifier.weight(2f))
        Image(painter = image, contentDescription = "Sydney",
            modifier = Modifier.padding(30.dp)
                //.weight(8f)
                .border(BorderStroke(20.dp, Color.White))
                .background(color = Color.Yellow)
                .shadow(20.dp)
        )
        Spacer(modifier = Modifier.weight(2f))
        Column(
            modifier = Modifier
                //.background(color = Color.Blue)
                .weight(2f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = stringResource(titleTextId), fontFamily = FontFamily.Default, fontSize = 24.sp, textAlign = TextAlign.Center)
            Text(text = stringResource(authorTextId), fontFamily = FontFamily.Default, fontSize = 16.sp, textAlign = TextAlign.Center, fontWeight = ExtraBold)
        }
        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Box(modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = onPreviousClick,
                modifier = Modifier.align(Alignment.CenterStart).padding(start = 20.dp, bottom = 20.dp)
            ) {
                Text("Previous", fontSize = 12.sp)
            }
            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 20.dp, bottom = 20.dp)
            ) {
                Text("Next", fontSize = 12.sp)
            }
        }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtApp()
    }
}