package com.example.chatproject.models

data class ChatMessage(
    val type: String,
    val sender: String,
    val roomId: String,
    val data: String
)