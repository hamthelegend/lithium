package com.thebrownfoxx.lithium.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thebrownfoxx.lithium.AppViewModelProvider
import com.thebrownfoxx.lithium.ui.screen.ScreenTransitions
import com.thebrownfoxx.lithium.ui.screen.destinations.CheckInDestination

@Destination(start = true, style = ScreenTransitions::class)
@Composable
fun Home(
    navigator: DestinationsNavigator,
) {
    val viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)

    viewModel.apply {
        val checkInsByDate by checkInsByDate.collectAsStateWithLifecycle()
        val feelingCategoriesToday by feelingCategoriesToday.collectAsStateWithLifecycle()
        val showUndoButton by showUndoButton.collectAsStateWithLifecycle()

        HomeScreen(
            checkInsByDate = checkInsByDate,
            feelingCategoriesToday = feelingCategoriesToday,
            onCheckIn = { navigator.navigate(CheckInDestination) },
            showUndoButton = showUndoButton,
            onDeleteCheckIn = ::onDeleteCheckIn,
            onUndoDeleteCheckIn = ::onUndoDeleteCheckIn,
        )
    }
}