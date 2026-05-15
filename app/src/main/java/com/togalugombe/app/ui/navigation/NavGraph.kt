package com.togalugombe.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.togalugombe.app.ui.screens.home.HomeScreen
import com.togalugombe.app.ui.screens.liveassist.LiveAssistScreen
import com.togalugombe.app.ui.screens.liveassist.SceneDetailScreen
import com.togalugombe.app.ui.screens.gallery.PuppetGalleryScreen
import com.togalugombe.app.ui.screens.gallery.PuppetDetailScreen
import com.togalugombe.app.ui.screens.artistconnect.ArtistConnectScreen
import com.togalugombe.app.ui.screens.artistconnect.WorkshopBookingScreen
import com.togalugombe.app.ui.screens.historyfeed.HistoryFeedScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToLiveAssist = { navController.navigate(Screen.LiveAssist.route) },
                onNavigateToGallery = { navController.navigate(Screen.PuppetGallery.route) },
                onNavigateToArtist = { navController.navigate(Screen.ArtistConnect.route) },
                onNavigateToHistory = { navController.navigate(Screen.HistoryFeed.route) }
            )
        }

        composable(Screen.LiveAssist.route) {
            LiveAssistScreen(
                onPlaySelected = { playId ->
                    navController.navigate(Screen.SceneDetail.createRoute(playId, 0))
                }
            )
        }

        composable(
            route = Screen.SceneDetail.route,
            arguments = listOf(
                navArgument("playId") { type = NavType.IntType },
                navArgument("sceneIndex") { type = NavType.IntType; defaultValue = 0 }
            )
        ) { backStackEntry ->
            SceneDetailScreen(
                playId = backStackEntry.arguments?.getInt("playId") ?: 1,
                initialSceneIndex = backStackEntry.arguments?.getInt("sceneIndex") ?: 0,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.PuppetGallery.route) {
            PuppetGalleryScreen(
                onPuppetSelected = { puppetId ->
                    navController.navigate(Screen.PuppetDetail.createRoute(puppetId))
                }
            )
        }

        composable(
            route = Screen.PuppetDetail.route,
            arguments = listOf(navArgument("puppetId") { type = NavType.IntType })
        ) { backStackEntry ->
            PuppetDetailScreen(
                puppetId = backStackEntry.arguments?.getInt("puppetId") ?: 1,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.ArtistConnect.route) {
            ArtistConnectScreen(
                onWorkshopSelected = { workshopId ->
                    navController.navigate(Screen.WorkshopBooking.createRoute(workshopId))
                }
            )
        }

        composable(
            route = Screen.WorkshopBooking.route,
            arguments = listOf(navArgument("workshopId") { type = NavType.IntType })
        ) { backStackEntry ->
            WorkshopBookingScreen(
                workshopId = backStackEntry.arguments?.getInt("workshopId") ?: 1,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.HistoryFeed.route) {
            HistoryFeedScreen()
        }
    }
}
