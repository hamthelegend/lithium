package com.thebrownfoxx.lithium.ui.screen.breakdown

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
fun Breakdown(navigator: DestinationsNavigator) {
    val viewModel: BreakdownViewModel = viewModel(factory = AppViewModelProvider.Factory)
    
    viewModel.apply { 
        val breakdowns by breakdowns.collectAsStateWithLifecycle()
        val breakdownPeriod by breakdownPeriod.collectAsStateWithLifecycle()
        val datePeriods by datePeriods.collectAsStateWithLifecycle()
        val selectedDatePeriod by selectedDatePeriod.collectAsStateWithLifecycle()
        val breakdown by breakdown.collectAsStateWithLifecycle()

        BreakdownScreen(
            breakdown = breakdown,
            breakdownPeriod = breakdownPeriod,
            onToggleBreakdownPeriod = ::onToggleBreakdownPeriod,
            datePeriods = datePeriods,
            selectedDatePeriod = selectedDatePeriod,
            onSelectedDatePeriodChange = ::onSelectedDatePeriodChange,
            onNavigateUp = { navigator.navigateUp() },
        )
    }
}