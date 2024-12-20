package com.example.chatproject.models

data class JoinMessage(
    val type: String,
    val sender: String,
    val roomId: String,
)
