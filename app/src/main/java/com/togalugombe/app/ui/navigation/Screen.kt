package com.togalugombe.app.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object LiveAssist : Screen("live_assist")
    object SceneDetail : Screen("scene_detail/{playId}/{sceneIndex}") {
        fun createRoute(playId: Int, sceneIndex: Int) = "scene_detail/$playId/$sceneIndex"
    }
    object PuppetGallery : Screen("puppet_gallery")
    object PuppetDetail : Screen("puppet_detail/{puppetId}") {
        fun createRoute(puppetId: Int) = "puppet_detail/$puppetId"
    }
    object ArtistConnect : Screen("artist_connect")
    object WorkshopBooking : Screen("workshop_booking/{workshopId}") {
        fun createRoute(workshopId: Int) = "workshop_booking/$workshopId"
    }
    object HistoryFeed : Screen("history_feed")
}
