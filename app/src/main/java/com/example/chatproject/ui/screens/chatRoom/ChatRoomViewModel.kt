package com.example.chatproject.ui.screens.chatRoom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatproject.data.repository.SocketRepository
import com.example.chatproject.models.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatRoomViewModel @Inject constructor(
    private val socketRepository: SocketRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val chatRoomId: String = savedStateHandle["chatRoomId"] ?: ""

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages
    private val listMessages: List<ChatMessage> = listOf(
        ChatMessage("1", "1", "1", "Este mensaje es nuevo"),
        ChatMessage("1", "0", "1", "Nuevo mensaje para ti"),
        ChatMessage("1", "1", "1", "Este no es nada"),
        ChatMessage("1", "1", "1", "Este nuevo mensaje"),
        ChatMessage("1", "0", "1", "Hola nuevo mensaje"),
        ChatMessage("1", "0", "1", "El chat funciona"),
    )


    init {
        println("chatroomid: $chatRoomId")
        connect()
        joinRoom(chatRoomId)
        socketRepository.setOnNewMessageListener { message ->
            println("New message received: $message")
            _messages.value += message
        }
        _messages.value = listMessages

    }


    fun addMessage(message: ChatMessage) {
        val currentMessages = _messages.value.toMutableList()
        currentMessages.add(message)
        _messages.value = currentMessages
        socketRepository.sendMessage(message.data)
    }

    fun connect() {
        viewModelScope.launch {
            socketRepository.connect()
        }
    }

    fun joinRoom(roomId: String) {
        viewModelScope.launch {
            socketRepository.joinRoom(roomId)
        }

    }

    fun disconnect() {
        viewModelScope.launch {
            socketRepository.disconnect()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            socketRepository.disconnect()
        }
    }
}