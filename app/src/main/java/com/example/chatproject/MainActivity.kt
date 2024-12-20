package com.example.chatproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.chatproject.data.local.WebSocketClient
import com.example.chatproject.ui.screens.MenuScreen
import com.example.chatproject.ui.theme.ChatProjectTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatProjectTheme {
                val messages = remember { mutableStateListOf<String>() }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(verticalArrangement = Arrangement.Center) {
                        MenuScreen(modifier = Modifier.padding(innerPadding))
                        /*Greeting(name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        messages = messages,
                        onMessageReceived = { message -> messages.add(message) })*/
                    }
                }
            }
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    messages: List<String>,
    onMessageReceived: (String) -> Unit
) {
    var webSocket: WebSocket? = null



    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(), horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                val client =
                    OkHttpClient()/*val request: Request = Request.Builder().url("ws://192.168.3.14:9090/chat").build()*/
                val request: Request = Request.Builder()
                    .url("ws://stunning-space-umbrella-xxwj9949wvvc69rp-9090.app.github.dev/chat")
                    .build()
                /*val listener = WebSocketClient(onMessageReceived)*/
                /*webSocket = client.newWebSocket(request, listener)*/
            }) {
                Text("Connect")
            }
            Button(onClick = {
                webSocket?.close(WebSocketClient.NORMAL_CLOSURE_STATUS, "Goodbye!")
                webSocket = null
            }) {
                Text("Disconnect")

            }
        }
        LazyColumn {
            messages.forEach { message ->
                item {
                    Text(text = message)
                }
            }
        }
    }


}

