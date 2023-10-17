package com.thebrownfoxx.lithium.ui.screen

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle
import com.thebrownfoxx.lithium.ui.screen.animation.sharedZAxisEnter
import com.thebrownfoxx.lithium.ui.screen.animation.sharedZAxisExit

object ScreenTransitions : DestinationStyle.Animated {
    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition() =
        sharedZAxisEnter(forward = true)

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition() =
        sharedZAxisExit(forward = true)

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition() =
        sharedZAxisEnter(forward = false)

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition() =
        sharedZAxisExit(forward = false)
}