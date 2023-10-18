package com.thebrownfoxx.lithium.domain

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

data class CheckIn(
    val id: Int? = null,
    val feeling: Feeling,
    val instant: Instant,
) {
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
}