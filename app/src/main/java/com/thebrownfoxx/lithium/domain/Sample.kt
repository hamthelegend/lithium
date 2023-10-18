package com.thebrownfoxx.lithium.domain

import java.time.Instant
import kotlin.random.Random
import kotlin.time.Duration.Companion.days

object Sample {
    private val random = Random(69)

    val CheckIns = Array(730) { index ->
        CheckIn(
            id = index,
            feeling = Feeling.entries.random(random),
            instant = randomInstant(),
        )
    }.sortedByDescending { it.instant }

    private fun randomInstant() = Instant.now().minusMillis(random.nextLong(until = 730.days.inWholeMilliseconds))
}