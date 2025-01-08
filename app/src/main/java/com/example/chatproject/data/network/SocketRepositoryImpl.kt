package com.example.chatproject.data.network

import com.example.chatproject.BuildConfig
import com.example.chatproject.data.local.WebSocketClient
import com.example.chatproject.data.repository.SocketRepository
import com.example.chatproject.models.ChatMessage
import com.example.chatproject.models.JoinMessage
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import javax.inject.Inject

class SocketRepositoryImpl @Inject constructor(private val webSocketClient: WebSocketClient) :
    SocketRepository {
    private var webSocket: WebSocket? = null
    private val gson = Gson()

    private var gitKey = BuildConfig.GITHUB_TOKEN
    private var globalUrl = BuildConfig.GLOBAL_URL

    private var onNewMessage: (ChatMessage) -> Unit = {}
    init {
        webSocketClient.onMessageReceived = { message ->
            onNewMessage(message)
        }
    }

    override fun setOnNewMessageListener(listener: (ChatMessage) -> Unit) {
        onNewMessage = listener
    }

    override fun connect() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(globalUrl)
            .addHeader("X-Github-Token", gitKey)
            .build()
        webSocket = client.newWebSocket(request, webSocketClient)
    }

    override fun disconnect() {
        webSocket?.close(WebSocketClient.NORMAL_CLOSURE_STATUS, "Goodbye!")
    }

    override fun sendMessage(message: String) {
        val chatMessage = ChatMessage("say", "Android", "room1", message)
        val jsonMessage = gson.toJson(chatMessage)
        webSocket?.send(jsonMessage)
    }

    override fun joinRoom(roomId: String) {
        val joinMessage = JoinMessage("join", "Android", roomId)
        val jsonMessage = gson.toJson(joinMessage)
        webSocket?.send(jsonMessage)
    }


}