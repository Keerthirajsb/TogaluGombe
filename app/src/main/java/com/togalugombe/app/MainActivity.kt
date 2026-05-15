package com.togalugombe.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.togalugombe.app.ui.components.BottomNavBar
import com.togalugombe.app.ui.navigation.NavGraph
import com.togalugombe.app.ui.navigation.Screen
import com.togalugombe.app.ui.theme.DeepBlack
import com.togalugombe.app.ui.theme.TogaluGombeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TogaluGombeTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Routes that show bottom nav
                val showBottomBar = currentRoute in listOf(
                    Screen.Home.route,
                    Screen.LiveAssist.route,
                    Screen.PuppetGallery.route,
                    Screen.ArtistConnect.route,
                    Screen.HistoryFeed.route
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = DeepBlack,
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavBar(
                                currentRoute = currentRoute,
                                onNavigate = { screen ->
                                    navController.navigate(screen.route) {
                                        popUpTo(Screen.Home.route) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavGraph(navController = navController)
                }
            }
        }
    }
}
