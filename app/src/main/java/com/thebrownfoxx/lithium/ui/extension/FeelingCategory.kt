package com.thebrownfoxx.lithium.ui.extension

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.thebrownfoxx.lithium.R
import com.thebrownfoxx.lithium.domain.FeelingCategory
import com.thebrownfoxx.lithium.domain.FeelingCategory.HighEnergyPleasant
import com.thebrownfoxx.lithium.domain.FeelingCategory.HighEnergyUnpleasant
import com.thebrownfoxx.lithium.domain.FeelingCategory.LowEnergyPleasant
import com.thebrownfoxx.lithium.domain.FeelingCategory.LowEnergyUnpleasant
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
            HighEnergyPleasant ->
                if (darkTheme) HighEnergyPleasantDarkColors.secondaryContainer
                else HighEnergyPleasantLightColors.secondaryContainer

            HighEnergyUnpleasant ->
                if (darkTheme) HighEnergyUnpleasantDarkColors.secondaryContainer
                else HighEnergyUnpleasantLightColors.secondaryContainer

            LowEnergyPleasant ->
                if (darkTheme) LowEnergyPleasantDarkColors.secondaryContainer
                else LowEnergyPleasantLightColors.secondaryContainer

            LowEnergyUnpleasant ->
                if (darkTheme) LowEnergyUnpleasantDarkColors.secondaryContainer
                else LowEnergyUnpleasantLightColors.secondaryContainer
        }
    }

val FeelingCategory.icon @Composable get() = when (this) {
    HighEnergyPleasant -> ImageVector.vectorResource(R.drawable.high_energy_pleasant)
    HighEnergyUnpleasant -> ImageVector.vectorResource(R.drawable.high_energy_unpleasant)
    LowEnergyPleasant -> ImageVector.vectorResource(R.drawable.low_energy_pleasant)
    LowEnergyUnpleasant -> ImageVector.vectorResource(R.drawable.low_energy_unpleasant)
}

val FeelingCategory.iconContentDescription @Composable get() = when (this) {
    HighEnergyPleasant -> stringResource(R.string.high_energy_pleasant)
    HighEnergyUnpleasant -> stringResource(R.string.high_energy_unpleasant)
    LowEnergyPleasant -> stringResource(R.string.low_energy_pleasant)
    LowEnergyUnpleasant -> stringResource(R.string.low_energy_unpleasant)
}