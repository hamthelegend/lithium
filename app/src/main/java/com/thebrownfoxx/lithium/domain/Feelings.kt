package com.thebrownfoxx.lithium.domain

import androidx.annotation.StringRes
import com.thebrownfoxx.lithium.R

data class Feeling(
    @StringRes val nameResourceId: Int,
    @StringRes val descriptionResourceId: Int
)

object Feelings {
    object HighEnergy {
        val Pleasant = listOf(
            Feeling(R.string.blissful, R.string.blissful_description),
            Feeling(R.string.buoyant, R.string.buoyant_description),
            Feeling(R.string.elated, R.string.elated_description),
            Feeling(R.string.energetic, R.string.energetic_description),
            Feeling(R.string.excited, R.string.excited_description),
            Feeling(R.string.enthusiastic, R.string.enthusiastic_description),
            Feeling(R.string.fecund, R.string.fecund_description),
            Feeling(R.string.jubilant, R.string.jubilant_description),
            Feeling(R.string.joyful, R.string.joyful_description),
            Feeling(R.string.optimistic, R.string.optimistic_description)
        )

        val Unpleasant = listOf(
            Feeling(R.string.agitated, R.string.agitated_description),
            Feeling(R.string.anxious, R.string.anxious_description),
            Feeling(R.string.frustrated, R.string.frustrated_description),
            Feeling(R.string.hectic, R.string.hectic_description),
            Feeling(R.string.horny, R.string.horny_description),
            Feeling(R.string.irritated, R.string.irritated_description),
            Feeling(R.string.nervous, R.string.nervous_description),
            Feeling(R.string.overwhelmed, R.string.overwhelmed_description),
            Feeling(R.string.panicked, R.string.panicked_description),
            Feeling(R.string.stressed, R.string.stressed_description)
        )
    }

    object LowEnergy {
        val Pleasant = listOf(
            Feeling(R.string.calm, R.string.calm_description),
            Feeling(R.string.cozy, R.string.cozy_description),
            Feeling(R.string.gentle, R.string.gentle_description),
            Feeling(R.string.peaceful, R.string.peaceful_description),
            Feeling(R.string.pleased, R.string.pleased_description),
            Feeling(R.string.relaxed, R.string.relaxed_description),
            Feeling(R.string.relieved, R.string.relieved_description),
            Feeling(R.string.serene, R.string.serene_description),
            Feeling(R.string.soothing, R.string.soothing_description),
            Feeling(R.string.tranquil, R.string.tranquil_description)
        )

        val Unpleasant = listOf(
            Feeling(R.string.apathetic, R.string.apathetic_description),
            Feeling(R.string.bored, R.string.bored_description),
            Feeling(R.string.depressed, R.string.depressed_description),
            Feeling(R.string.gloomy, R.string.gloomy_description),
            Feeling(R.string.lethargic, R.string.lethargic_description),
            Feeling(R.string.melancholic, R.string.melancholic_description),
            Feeling(R.string.miserable, R.string.miserable_description),
            Feeling(R.string.sad, R.string.sad_description),
            Feeling(R.string.uninterested, R.string.uninterested_description)
        )
    }
}