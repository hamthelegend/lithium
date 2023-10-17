package com.thebrownfoxx.lithium.ui.extension

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.thebrownfoxx.lithium.domain.FeelingCategory
import com.thebrownfoxx.lithium.ui.theme.color.HighEnergyPleasantDarkColors
import com.thebrownfoxx.lithium.ui.theme.color.HighEnergyPleasantLightColors
import com.thebrownfoxx.lithium.ui.theme.color.HighEnergyUnpleasantDarkColors
import com.thebrownfoxx.lithium.ui.theme.color.HighEnergyUnpleasantLightColors
import com.thebrownfoxx.lithium.ui.theme.color.LowEnergyPleasantDarkColors
import com.thebrownfoxx.lithium.ui.theme.color.LowEnergyPleasantLightColors
import com.thebrownfoxx.lithium.ui.theme.color.LowEnergyUnpleasantDarkColors
import com.thebrownfoxx.lithium.ui.theme.color.LowEnergyUnpleasantLightColors

val FeelingCategory.color: Color
    @Composable get() {
        val darkTheme = isSystemInDarkTheme()
        return when (this) {
            FeelingCategory.HighEnergyPleasant ->
                if (darkTheme) HighEnergyPleasantDarkColors.primaryContainer
                else HighEnergyPleasantLightColors.primaryContainer

            FeelingCategory.HighEnergyUnpleasant ->
                if (darkTheme) HighEnergyUnpleasantDarkColors.primaryContainer
                else HighEnergyUnpleasantLightColors.primaryContainer

            FeelingCategory.LowEnergyPleasant ->
                if (darkTheme) LowEnergyPleasantDarkColors.primaryContainer
                else LowEnergyPleasantLightColors.primaryContainer

            FeelingCategory.LowEnergyUnpleasant ->
                if (darkTheme) LowEnergyUnpleasantDarkColors.primaryContainer
                else LowEnergyUnpleasantLightColors.primaryContainer
        }
    }