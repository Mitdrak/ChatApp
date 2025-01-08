package com.example.chatproject.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.chatproject.ui.screens.chatRoom.ChatRoomScreen
import com.example.chatproject.ui.screens.menu.MenuScreen


fun NavGraphBuilder.authenticatedGraph(navController: NavController) {
    navigation(
        route = NavigationRoutes.Authenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Authenticated.Menu.route
    ) {
        //Menu
        composable(route = NavigationRoutes.Authenticated.Menu.route) {
            MenuScreen(navController = navController)
        }
        //ChatRoom
        composable(route = NavigationRoutes.Authenticated.ChatRoom.route) { backStackEntry ->
            val chatRoomId = backStackEntry.arguments?.getString("chatRoomId")
            ChatRoomScreen(navController = navController, chatRoomId = chatRoomId)
        }
    }
}