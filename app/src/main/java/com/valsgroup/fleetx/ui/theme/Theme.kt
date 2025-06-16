package com.valsgroup.fleetx.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = OrangePrimary,           // Orange buttons
    onPrimary = White,                 // White text on orange buttons
    primaryContainer = OrangeLight,
    onPrimaryContainer = Black,
    secondary = GrayMedium,
    onSecondary = White,
    tertiary = Pink80,
    onTertiary = Black,
    background = GrayDark,
    onBackground = White,
    surface = GrayDark,
    onSurface = White,
    surfaceVariant = GrayMedium,
    onSurfaceVariant = White,
    outline = GrayBorder
)

private val LightColorScheme = lightColorScheme(
    primary = OrangePrimary,           // Orange buttons
    onPrimary = White,                 // White text on orange buttons
    primaryContainer = OrangeLight,
    onPrimaryContainer = Black,
    secondary = GrayMedium,
    onSecondary = White,
    tertiary = Pink40,
    onTertiary = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    surfaceVariant = GrayLight,
    onSurfaceVariant = GrayDark,
    outline = GrayBorder
)

@Composable
fun FleetXTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disable dynamic color to ensure our orange theme
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}