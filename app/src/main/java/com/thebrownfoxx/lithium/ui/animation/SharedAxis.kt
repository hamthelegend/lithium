package com.thebrownfoxx.lithium.ui.animation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

fun Density.sharedXAxis(forward: Boolean = true) =
    slideInHorizontally(
        animationSpec = tween(durationMillis = 300)
    ) {
        val directionMultiplier = if (forward) 1 else -1
        with(this) { (30 * directionMultiplier).dp.toPx().toInt() }
    } + fadeIn(
        animationSpec = tween(
            delayMillis = 90,
            durationMillis = 210,
        ),
    ) togetherWith slideOutHorizontally(
        animationSpec = tween(durationMillis = 300)
    ) {
        val directionMultiplier = if (forward) -1 else 1
        with(this) { (30 * directionMultiplier).dp.toPx().toInt() }
    } + fadeOut(animationSpec = tween(durationMillis = 90))

fun sharedZAxis(forward: Boolean = true) =
    sharedZAxisEnter(forward) togetherWith sharedZAxisExit(forward)

fun sharedZAxisEnter(forward: Boolean = true) = scaleIn(
    animationSpec = tween(durationMillis = 300),
    initialScale = if (forward) 0.8f else 1.1f,
) + fadeIn(
    animationSpec = tween(
        delayMillis = 90,
        durationMillis = 210,
    ),
)

fun sharedZAxisExit(forward: Boolean = true) = scaleOut(
    animationSpec = tween(durationMillis = 300),
    targetScale = if (forward) 1.1f else 0.8f,
) + fadeOut(animationSpec = tween(durationMillis = 90))
