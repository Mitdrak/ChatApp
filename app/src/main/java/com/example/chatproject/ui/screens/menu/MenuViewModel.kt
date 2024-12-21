package com.example.chatproject.ui.screens.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatproject.data.repository.SocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MenuViewModel @Inject constructor(private val socketRepository: SocketRepository) :
    ViewModel() {

    fun connect() {
        viewModelScope.launch {
            socketRepository.connect()
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            socketRepository.disconnect()
        }
    }
    fun joinRoom() {
        viewModelScope.launch {
            socketRepository.joinRoom()
        }
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            socketRepository.sendMessage(message)
        }
    }
}