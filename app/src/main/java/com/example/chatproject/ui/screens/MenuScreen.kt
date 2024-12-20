package com.example.chatproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatproject.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(modifier: Modifier = Modifier, viewModel: MenuViewModel = viewModel()) {
    //Cantidad de salas
    val list: List<String> = listOf(
        "Sala 1",
        "Sala 2",
        "Sala 3",
        "Sala 4",
        "Sala 5",
        "Sala 6",
        "Sala 7",
        "Sala 8",
        "Sala 9",
        "Sala 10"
    )
    val lasMessages = listOf(
        "Hoy es un dia soleado",
        "Hola, como estas?",
        "Estoy bien, gracias",
        "Que haces?",
        "Estoy trabajando",
        "Que bien",
        "Si, es un buen dia",
        "Si, es un buen dia",
        "No quiero habar hoy, mejor mañana",
        "Ok, nos vemos"
    )
    TopAppBar(
        title = {
            Text("Chat App")
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        )
    )
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(list.count()) { message ->
                Card(modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                println("Clicked at $list[message]")
                            },
                        horizontalArrangement = Arrangement.Start
                    ) {
                        CircularImage(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            modifier = Modifier.size(50.dp),
                            contentDescription = "Profile Picture"
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Column {
                            Text(text = list[message])
                            Text(text = lasMessages[message])
                        }
                    }


                }
            }
        }
    }

    /*Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(), horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                viewModel.connect()
            }) {
                Text("Connect")
            }
            Button(onClick = {
                viewModel.disconnect()
            }) {
                Text("Disconnect")

            }
        }
        Row {
            Button(onClick = {
                viewModel.joinRoom()
            }) {
                Text("Join Room")
            }
            Button(onClick = {
                viewModel.sendMessage("Hello from Android")
            }) { }
        }
        LazyColumn {
            *//*messages.forEach { message ->
                item {
                    Text(text = message)
                }
            }*//*
        }
    }*/
}

@Composable
fun CircularImage(
    painter: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    size: Dp = 40.dp,
    borderWidth: Dp = 0.dp,
    borderColor: Color = Color.Transparent,
    backgroundColor: Color = Color.LightGray, // Placeholder background color
    contentScale: ContentScale = ContentScale.Crop,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor) // Apply background color for placeholder
            .border(borderWidth, borderColor, CircleShape)
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.matchParentSize(),
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
        )
    }
}

@Preview
@Composable
fun PreviewMenuScreen() {
    MenuScreen()
}