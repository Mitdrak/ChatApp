package com.example.chatproject

import android.util.Log
import com.example.chatproject.models.ChatMessage
import com.google.gson.Gson
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONArray
import org.json.JSONObject

class WebSocketClienta(private val onMessageReceived: (String) -> Unit) : WebSocketListener() {
    private val gson = Gson()

    override fun onOpen(webSocket: WebSocket, response: Response) {
        val joinMessage = """
            {
              "type": "join",
              "sender": "Android",
              "roomId": "room1"
            }
        """.trimIndent()
        /*val mensaje = ChatMessage("Android User", "join entro", "join")*/
        /*val jsonMessage = gson.toJson(mensaje)
        webSocket.send(joinMessage)*/
        Log.e("burak", "Connected")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        output("Received : $text")
        val jsonObject = JSONObject(text)
        val dataHeader = jsonObject.getString("type")
        if (dataHeader == "say") {
            val data = jsonObject.getString("data")
            onMessageReceived(data)
        }
        if (dataHeader == "join") {
            val data = jsonObject.getString("data")
            val jsonArray = JSONArray(data)
            val names = mutableListOf<String>()
            for (i in 0 until jsonArray.length()) {
                val userObject = jsonArray.getJSONObject(i)
                val name = userObject.getString("name")
                onMessageReceived("User $name joined the chat")
            }
        }
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null)
        output("Closing : $code / $reason")
    }


    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        output("Error : " + t.message + "fsda")
    }

    fun output(text: String?) {
        Log.d("PieSocket", text!!)
    }

    companion object {
        const val NORMAL_CLOSURE_STATUS = 1000
    }
}