package com.thebrownfoxx.lithium.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.thebrownfoxx.lithium.AppViewModelProvider

@Destination(start = true)
@Composable
fun Home() {
    val viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)

    viewModel.apply {
        val checkInsByDate by checkInsByDate.collectAsStateWithLifecycle()
        val feelingCategoriesToday by feelingCategoriesToday.collectAsStateWithLifecycle()

        HomeScreen(
            checkInsByDate = checkInsByDate,
            feelingCategoriesToday = feelingCategoriesToday,
            onCheckIn = { /*TODO*/ },
            onDeleteCheckIn = { /*TODO */ },
        )
    }
}