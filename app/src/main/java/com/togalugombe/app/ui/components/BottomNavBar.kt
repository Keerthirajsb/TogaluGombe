package com.togalugombe.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.TheaterComedy
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.TheaterComedy
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.togalugombe.app.R
import com.togalugombe.app.ui.navigation.Screen
import com.togalugombe.app.ui.theme.*

data class BottomNavItem(
    val screen: Screen,
    val labelResId: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(Screen.LiveAssist, R.string.live_assist, Icons.Filled.TheaterComedy, Icons.Outlined.TheaterComedy),
    BottomNavItem(Screen.PuppetGallery, R.string.puppet_gallery, Icons.Filled.Explore, Icons.Outlined.Explore),
    BottomNavItem(Screen.ArtistConnect, R.string.artist_connect, Icons.Filled.Groups, Icons.Outlined.Groups),
    BottomNavItem(Screen.HistoryFeed, R.string.history_feed, Icons.Filled.History, Icons.Outlined.History)
)

@Composable
fun BottomNavBar(currentRoute: String?, onNavigate: (Screen) -> Unit) {
    NavigationBar(
        containerColor = DarkSurface,
        contentColor = WarmAmber
    ) {
        bottomNavItems.forEach { item ->
            val selected = currentRoute == item.screen.route
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(item.screen) },
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = stringResource(item.labelResId)
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.labelResId),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = WarmAmber,
                    selectedTextColor = WarmAmber,
                    unselectedIconColor = SoftCream,
                    unselectedTextColor = SoftCream,
                    indicatorColor = DarkSurfaceVariant
                )
            )
        }
    }
}
