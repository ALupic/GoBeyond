package com.example.gobeyond.ui.data.components

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun DynamicSystemNavBarColor(
    color: Color,
    darkIcons: Boolean
) {
    val view = LocalView.current
    val window = (view.context as? Activity)?.window ?: return

    SideEffect {
        window.navigationBarColor = color.toArgb()
        WindowInsetsControllerCompat(window, view)
            .isAppearanceLightNavigationBars = darkIcons
    }
}