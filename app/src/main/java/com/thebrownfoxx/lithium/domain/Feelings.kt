package com.thebrownfoxx.lithium.domain

import androidx.annotation.StringRes
import com.thebrownfoxx.lithium.R

data class Feeling(
    val id: Int,
    @StringRes val nameResourceId: Int,
    @StringRes val descriptionResourceId: Int
) {
    companion object {
        fun ofId(id: Int) = Feelings.All.single { it.id == id }
    }
}

object Feelings {
    object HighEnergy {
        val Pleasant = listOf(
            Feeling(1, R.string.blissful, R.string.blissful_description),
            Feeling(2, R.string.buoyant, R.string.buoyant_description),
            Feeling(3, R.string.elated, R.string.elated_description),
            Feeling(4, R.string.energetic, R.string.energetic_description),
            Feeling(5, R.string.excited, R.string.excited_description),
            Feeling(6, R.string.enthusiastic, R.string.enthusiastic_description),
            Feeling(7, R.string.fecund, R.string.fecund_description),
            Feeling(8, R.string.jubilant, R.string.jubilant_description),
            Feeling(9, R.string.joyful, R.string.joyful_description),
            Feeling(10, R.string.optimistic, R.string.optimistic_description),
        )

        val Unpleasant = listOf(
            Feeling(11, R.string.agitated, R.string.agitated_description),
            Feeling(12, R.string.anxious, R.string.anxious_description),
            Feeling(13, R.string.frustrated, R.string.frustrated_description),
            Feeling(14, R.string.hectic, R.string.hectic_description),
            Feeling(15, R.string.horny, R.string.horny_description),
            Feeling(16, R.string.irritated, R.string.irritated_description),
            Feeling(17, R.string.nervous, R.string.nervous_description),
            Feeling(18, R.string.overwhelmed, R.string.overwhelmed_description),
            Feeling(19, R.string.panicked, R.string.panicked_description),
            Feeling(20, R.string.stressed, R.string.stressed_description),
        )
    }

    object LowEnergy {
        val Pleasant = listOf(
            Feeling(21, R.string.calm, R.string.calm_description),
            Feeling(22, R.string.cozy, R.string.cozy_description),
            Feeling(23, R.string.gentle, R.string.gentle_description),
            Feeling(24, R.string.peaceful, R.string.peaceful_description),
            Feeling(25, R.string.pleased, R.string.pleased_description),
            Feeling(26, R.string.relaxed, R.string.relaxed_description),
            Feeling(27, R.string.relieved, R.string.relieved_description),
            Feeling(28, R.string.serene, R.string.serene_description),
            Feeling(29, R.string.soothing, R.string.soothing_description),
            Feeling(30, R.string.tranquil, R.string.tranquil_description),
        )

        val Unpleasant = listOf(
            Feeling(31, R.string.apathetic, R.string.apathetic_description),
            Feeling(32, R.string.bored, R.string.bored_description),
            Feeling(33, R.string.depressed, R.string.depressed_description),
            Feeling(34, R.string.gloomy, R.string.gloomy_description),
            Feeling(35, R.string.lethargic, R.string.lethargic_description),
            Feeling(36, R.string.melancholic, R.string.melancholic_description),
            Feeling(37, R.string.miserable, R.string.miserable_description),
            Feeling(38, R.string.sad, R.string.sad_description),
            Feeling(39, R.string.uninterested, R.string.uninterested_description),
            Feeling(40, R.string.resentful, R.string.resentful_description),
        )
    }

    val All = HighEnergy.Pleasant + HighEnergy.Unpleasant + LowEnergy.Pleasant + LowEnergy.Unpleasant
}

