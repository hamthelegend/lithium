package com.thebrownfoxx.lithium.ui.screen.checkin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thebrownfoxx.lithium.AppViewModelProvider
import com.thebrownfoxx.lithium.ui.screen.ScreenTransitions

@Destination(style = ScreenTransitions::class)
@Composable
fun CheckIn(
    navigator: DestinationsNavigator,
) {
    val viewModel: CheckInViewModel = viewModel(factory = AppViewModelProvider.Factory)

    viewModel.apply {
        val feelingCategory by viewModel.feelingCategory.collectAsStateWithLifecycle()
        val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
        val feelings by viewModel.feelings.collectAsStateWithLifecycle()

        CheckInScreen(
            feelings = feelings,
            onCheckIn = ::onCheckIn,
            feelingCategory = feelingCategory,
            onFeelingCategoryChange = ::onFeelingCategoryChange,
            searchQuery = searchQuery,
            onSearchQueryChange = ::onSearchQueryChange,
            onNavigateUp = { navigator.navigateUp() },
        )
    }
}