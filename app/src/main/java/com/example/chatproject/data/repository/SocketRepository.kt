package com.example.chatproject.data.repository

interface SocketRepository {
    fun connect()
    fun disconnect()
    fun joinRoom()
    fun sendMessage(message: String)
}