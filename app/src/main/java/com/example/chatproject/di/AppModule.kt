package com.example.chatproject.di

import com.example.chatproject.data.local.WebSocketClient
import com.example.chatproject.data.network.SocketRepositoryImpl
import com.example.chatproject.data.repository.SocketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideSocketClient(): WebSocketClient {
        return WebSocketClient(
            onMessageReceived = { message ->
                println("Message received: $message")
            },
        )
    }
    @Provides
    @Singleton
    fun provideSocketRepository(socketClient: WebSocketClient): SocketRepository {
        return SocketRepositoryImpl(socketClient)
    }

}