package com.example.chatproject.data.repository

import com.example.chatproject.models.ChatMessage

interface SocketRepository {
    fun connect()
    fun disconnect()
    fun joinRoom(roomId: String)
    fun sendMessage(message: String)
    fun setOnNewMessageListener(listener: (ChatMessage) -> Unit)
}