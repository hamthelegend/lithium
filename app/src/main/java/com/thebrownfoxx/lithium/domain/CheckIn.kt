package com.thebrownfoxx.lithium.domain

import java.time.Instant

data class CheckIn(
    val id: Int? = null,
    val feeling: Feeling,
    val instant: Instant,
)