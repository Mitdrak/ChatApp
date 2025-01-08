package com.example.chatproject.ui.navigation

sealed class NavigationRoutes {

    // Unauthenticated Routes
    /*sealed class Unauthenticated(val route: String) : NavigationRoutes() {
        object NavigationRoute : Unauthenticated(route = "unauthenticated")
        object Login : Unauthenticated(route = "login")
        object Registration : Unauthenticated(route = "registration")
    }*/

    // Authenticated Routes
    sealed class Authenticated(val route: String) : NavigationRoutes() {
        object NavigationRoute : Authenticated(route = "authenticated")
        object Menu : Authenticated(route = "Menu")
        object ChatRoom : Authenticated(route = "chatRoom/{chatRoomId}")

        fun chatRoomRoute(chatRoomId: String) = "chatRoom/$chatRoomId"
    }
}