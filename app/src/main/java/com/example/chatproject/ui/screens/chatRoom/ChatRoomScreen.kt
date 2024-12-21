package com.example.chatproject.ui.screens.chatRoom

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomScreen(navController: NavController) {
    Column {
        TopAppBar(title = {
            Text("CHAT 1")
        },
            navigationIcon = {
                IconButton(onClick = {

                }) {
                    Icon()
                }
            }

            )
        Text(
            text = "AAAAAAA"
        )
        println("YA EN SALA")
    }
}