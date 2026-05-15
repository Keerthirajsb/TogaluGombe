package com.togalugombe.app.ui.theme

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = WarmAmber,
    onPrimary = DeepBlack,
    primaryContainer = BurntUmber,
    onPrimaryContainer = WarmWhite,
    secondary = DeepSaffron,
    onSecondary = DeepBlack,
    secondaryContainer = DarkSurfaceVariant,
    onSecondaryContainer = AncientParchment,
    tertiary = MutedGold,
    onTertiary = DeepBlack,
    background = DeepBlack,
    onBackground = WarmWhite,
    surface = DarkSurface,
    onSurface = WarmWhite,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = SoftCream,
    error = VermillionRed,
    onError = WarmWhite,
    outline = DividerColor
)

@Composable
fun TogaluGombeTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = view.context.findActivity()?.window
            if (window != null) {
                window.statusBarColor = DeepBlack.toArgb()
                window.navigationBarColor = DarkSurface.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}

private fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}
