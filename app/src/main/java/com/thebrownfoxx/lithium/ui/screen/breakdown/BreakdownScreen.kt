package com.thebrownfoxx.lithium.ui.screen.breakdown

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.domain.Sample
import com.thebrownfoxx.lithium.domain.breakdown.Breakdown
import com.thebrownfoxx.lithium.domain.breakdown.toBreakdowns
import com.thebrownfoxx.lithium.ui.component.plus
import com.thebrownfoxx.lithium.ui.screen.breakdown.BreakdownPeriod.*
import com.thebrownfoxx.lithium.ui.screen.breakdown.component.BreakdownTopBar
import com.thebrownfoxx.lithium.ui.screen.breakdown.component.graph.BreakdownCards
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreakdownScreen(
    breakdown: Breakdown?,
    breakdownPeriod: BreakdownPeriod,
    onToggleBreakdownPeriod: () -> Unit,
    datePeriods: List<OpenEndRange<LocalDate>?>,
    selectedDatePeriod: OpenEndRange<LocalDate>?,
    onSelectedDatePeriodChange: (OpenEndRange<LocalDate>) -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BreakdownTopBar(
                breakdownPeriod = breakdownPeriod,
                onToggleBreakdownPeriod = onToggleBreakdownPeriod,
                datePeriods = datePeriods,
                selectedDatePeriod = selectedDatePeriod,
                onSelectedDatePeriodChange = onSelectedDatePeriodChange,
                onNavigateUp = onNavigateUp,
                scrollBehavior = scrollBehavior,
            )
        },
    ) { contentPadding ->
        if (breakdown != null) {
            BreakdownCards(
                breakdown = breakdown,
                contentPadding = contentPadding + PaddingValues(16.dp),
            )
        }
    }
}


@Preview
@Composable
fun BreakdownScreenPreview() {
    val breakdowns = remember { Sample.CheckIns.toBreakdowns() }

    var breakdownPeriod by remember { mutableStateOf(Monthly) }
    val breakdownsByPeriod = remember(breakdownPeriod, breakdowns) {
        when (breakdownPeriod) {
            AllTime -> listOf(breakdowns.allTime)
            Yearly -> breakdowns.yearly
            Monthly -> breakdowns.monthly
        }
    }
    val datePeriods = remember(breakdownsByPeriod) { breakdownsByPeriod.map { it?.datePeriod } }
    var selectedDatePeriod by remember(datePeriods) { mutableStateOf(datePeriods.first()) }
    val breakdown = remember(breakdownsByPeriod, selectedDatePeriod) {
        breakdownsByPeriod.single { it?.datePeriod == selectedDatePeriod }
    }

    LithiumTheme {
        BreakdownScreen(
            breakdown = breakdown,
            breakdownPeriod = breakdownPeriod,
            onToggleBreakdownPeriod = {
                breakdownPeriod = when (breakdownPeriod) {
                    AllTime -> Yearly
                    Yearly -> Monthly
                    Monthly -> AllTime
                }
            },
            datePeriods = datePeriods,
            selectedDatePeriod = selectedDatePeriod,
            onSelectedDatePeriodChange = { selectedDatePeriod = it },
            onNavigateUp = {},
        )
    }
}