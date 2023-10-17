package com.thebrownfoxx.lithium.domain

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.thebrownfoxx.lithium.R
import com.thebrownfoxx.lithium.domain.EnergyLevel.High
import com.thebrownfoxx.lithium.domain.EnergyLevel.Low
import com.thebrownfoxx.lithium.domain.FeelingCategory.HighEnergyPleasant
import com.thebrownfoxx.lithium.domain.FeelingCategory.HighEnergyUnpleasant
import com.thebrownfoxx.lithium.domain.FeelingCategory.LowEnergyPleasant
import com.thebrownfoxx.lithium.domain.FeelingCategory.LowEnergyUnpleasant

enum class FeelingCategory {
    HighEnergyPleasant,
    HighEnergyUnpleasant,
    LowEnergyPleasant,
    LowEnergyUnpleasant,
}

enum class EnergyLevel {
    High,
    Low,
}

enum class Feeling(
    val titleResourceId: Int,
    val descriptionResourceId: Int,
    val energyLevel: EnergyLevel,
    val pleasant: Boolean,
) {
    // High-Energy Pleasant
    Blissful(R.string.blissful, R.string.blissful_description, High, true),
    Buoyant(R.string.buoyant, R.string.buoyant_description, High, true),
    Enthusiastic(R.string.enthusiastic, R.string.enthusiastic_description, High, true),
    Elated(R.string.elated, R.string.elated_description, High, true),
    Energetic(R.string.energetic, R.string.energetic_description, High, true),
    Excited(R.string.excited, R.string.excited_description, High, true),
    Fecund(R.string.fecund, R.string.fecund_description, High, true),
    Jubilant(R.string.jubilant, R.string.jubilant_description, High, true),
    Joyful(R.string.joyful, R.string.joyful_description, High, true),
    Optimistic(R.string.optimistic, R.string.optimistic_description, High, true),

    // High-Energy Unpleasant
    Agitated(R.string.agitated, R.string.agitated_description, High, false),
    Anxious(R.string.anxious, R.string.anxious_description, High, false),
    Frustrated(R.string.frustrated, R.string.frustrated_description, High, false),
    Hectic(R.string.hectic, R.string.hectic_description, High, false),
    Horny(R.string.horny, R.string.horny_description, High, false),
    Irritated(R.string.irritated, R.string.irritated_description, High, false),
    Nervous(R.string.nervous, R.string.nervous_description, High, false),
    Overwhelmed(R.string.overwhelmed, R.string.overwhelmed_description, High, false),
    Panicked(R.string.panicked, R.string.panicked_description, High, false),
    Stressed(R.string.stressed, R.string.stressed_description, High, false),

    // Low-Energy Pleasant
    Calm(R.string.calm, R.string.calm_description, Low, true),
    Cozy(R.string.cozy, R.string.cozy_description, Low, true),
    Gentle(R.string.gentle, R.string.gentle_description, Low, true),
    Peaceful(R.string.peaceful, R.string.peaceful_description, Low, true),
    Pleased(R.string.pleased, R.string.pleased_description, Low, true),
    Relaxed(R.string.relaxed, R.string.relaxed_description, Low, true),
    Relieved(R.string.relieved, R.string.relieved_description, Low, true),
    Serene(R.string.serene, R.string.serene_description, Low, true),
    Soothing(R.string.soothing, R.string.soothing_description, Low, true),
    Tranquil(R.string.tranquil, R.string.tranquil_description, Low, true),

    // Low-Energy Unpleasant
    Apathetic(R.string.apathetic, R.string.apathetic_description, Low, false),
    Bored(R.string.bored, R.string.bored_description, Low, false),
    Depressed(R.string.depressed, R.string.depressed_description, Low, false),
    Gloomy(R.string.gloomy, R.string.gloomy_description, Low, false),
    Lethargic(R.string.lethargic, R.string.lethargic_description, Low, false),
    Melancholic(R.string.melancholic, R.string.melancholic_description, Low, false),
    Miserable(R.string.miserable, R.string.miserable_description, Low, false),
    Sad(R.string.sad, R.string.sad_description, Low, false),
    Uninterested(R.string.uninterested, R.string.uninterested_description, Low, false),
    Resentful(R.string.resentful, R.string.resentful_description, Low, false);

    val id get() = entries.indexOf(this)

    val category get() = when (energyLevel) {
        High -> if (pleasant) HighEnergyPleasant else HighEnergyUnpleasant
        Low -> if (pleasant) LowEnergyPleasant else LowEnergyUnpleasant
    }

    val title @Composable get() = stringResource(titleResourceId)

    val description @Composable get() = stringResource(descriptionResourceId)

    companion object {
        fun ofId(id: Int) = entries[id]

        fun ofCategory(category: FeelingCategory) = entries.filter {
            when (category) {
                HighEnergyPleasant -> it.energyLevel == High && it.pleasant
                HighEnergyUnpleasant -> it.energyLevel == High && !it.pleasant
                LowEnergyPleasant -> it.energyLevel == Low && it.pleasant
                LowEnergyUnpleasant -> it.energyLevel == Low && !it.pleasant
            }
        }
    }
}