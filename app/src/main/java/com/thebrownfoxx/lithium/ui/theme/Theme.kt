package com.thebrownfoxx.lithium.ui.theme

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.thebrownfoxx.lithium.domain.FeelingCategory
import com.thebrownfoxx.lithium.ui.theme.color.HighEnergyPleasantDarkColors
import com.thebrownfoxx.lithium.ui.theme.color.HighEnergyPleasantLightColors
import com.thebrownfoxx.lithium.ui.theme.color.HighEnergyUnpleasantDarkColors
import com.thebrownfoxx.lithium.ui.theme.color.HighEnergyUnpleasantLightColors
import com.thebrownfoxx.lithium.ui.theme.color.LowEnergyPleasantDarkColors
import com.thebrownfoxx.lithium.ui.theme.color.LowEnergyPleasantLightColors
import com.thebrownfoxx.lithium.ui.theme.color.LowEnergyUnpleasantDarkColors
import com.thebrownfoxx.lithium.ui.theme.color.LowEnergyUnpleasantLightColors

private val lightColors = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
)

private val darkColors = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
)

@Composable
fun LithiumTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    feelingCategory: FeelingCategory? = null,
    content: @Composable () -> Unit,
) {
    val colorScheme = when (feelingCategory) {
        FeelingCategory.HighEnergyPleasant ->
            if (darkTheme) HighEnergyPleasantDarkColors else HighEnergyPleasantLightColors

        FeelingCategory.HighEnergyUnpleasant ->
            if (darkTheme) HighEnergyUnpleasantDarkColors else HighEnergyUnpleasantLightColors

        FeelingCategory.LowEnergyPleasant ->
            if (darkTheme) LowEnergyPleasantDarkColors else LowEnergyPleasantLightColors

        FeelingCategory.LowEnergyUnpleasant ->
            if (darkTheme) LowEnergyUnpleasantDarkColors else LowEnergyUnpleasantLightColors

        null -> when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }

            else -> if (darkTheme) darkColors else lightColors
        }
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme.animated(),
        typography = Typography,
        content = content
    )
}