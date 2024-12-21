package com.example.chatproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.chatproject.ui.navigation.NavigationRoutes
import com.example.chatproject.ui.navigation.authenticatedGraph
import com.example.chatproject.ui.theme.ChatProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatProjectTheme {
                enableEdgeToEdge()

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }

            }
        }
    }
}

@Composable
fun MainApp() {

    MainAppNavHost()
}

@Composable
fun MainAppNavHost(
    modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationRoutes.Authenticated.NavigationRoute.route
    ) {
        authenticatedGraph(navController = navController)
    }
}

