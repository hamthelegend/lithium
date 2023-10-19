package com.thebrownfoxx.lithium.ui.screen.breakdown.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.R
import com.thebrownfoxx.lithium.domain.Sample
import com.thebrownfoxx.lithium.domain.breakdown.toBreakdowns
import com.thebrownfoxx.lithium.ui.component.Elevation
import com.thebrownfoxx.lithium.ui.component.ExpandedTopAppBar
import com.thebrownfoxx.lithium.ui.extension.ExpandedTopBarPreview
import com.thebrownfoxx.lithium.ui.screen.breakdown.BreakdownPeriod
import com.thebrownfoxx.lithium.ui.screen.breakdown.BreakdownPeriod.*
import com.thebrownfoxx.lithium.ui.theme.LithiumIcons
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreakdownTopBar(
    breakdownPeriod: BreakdownPeriod,
    onToggleBreakdownPeriod: () -> Unit,
    datePeriods: List<OpenEndRange<LocalDate>?>,
    selectedDatePeriod: OpenEndRange<LocalDate>?,
    onSelectedDatePeriodChange: (OpenEndRange<LocalDate>) -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    val text = when (breakdownPeriod) {
        AllTime -> stringResource(R.string.all_time_breakdown)
        Yearly -> stringResource(R.string.yearly_breakdown)
        Monthly -> stringResource(R.string.monthly_breakdown)
    }

    Column(modifier = modifier) {
        ExpandedTopAppBar(
            scrollBehavior = scrollBehavior,
            collapsedContent = {
                Text(text = text)
            },
            navigationIcon = {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = LithiumIcons.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                    )
                }
            },
            actions = {
                IconButton(onClick = onToggleBreakdownPeriod) {
                    Icon(
                        imageVector = LithiumIcons.DateRange,
                        contentDescription = stringResource(R.string.toggle_breakdown_period),
                    )
                }
            },
        ) {
            AnimatedContent(targetState = text) { text ->
                Text(text = text)
            }
        }
        AnimatedVisibility(visible = breakdownPeriod != AllTime) {
            if (selectedDatePeriod != null) {
                ScrollableTabRow(
                    selectedTabIndex = datePeriods.indexOf(selectedDatePeriod),
                    edgePadding = 16.dp,
                    containerColor = MaterialTheme.colorScheme
                        .surfaceColorAtElevation(Elevation.level(2)),
                    divider = {},
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    for (datePeriod in datePeriods) {
                        if (datePeriod != null) {
                            val periodText = when (breakdownPeriod) {
                                AllTime -> stringResource(R.string.all_time)
                                Yearly -> datePeriod.start.year.toString()
                                Monthly -> DateTimeFormatter.ofPattern("LLLL yyyy")
                                    .format(datePeriod.start)
                            }
                            Tab(
                                selected = datePeriod == selectedDatePeriod,
                                onClick = { onSelectedDatePeriodChange(datePeriod) },
                            ) {
                                Text(
                                    text = periodText,
                                    modifier = Modifier.padding(16.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BreakdownTopBarPreview() {
    val breakdowns = remember { Sample.CheckIns.toBreakdowns() }

    var breakdownPeriod by remember { mutableStateOf(Monthly) }
    val datePeriods = remember(breakdownPeriod) {
        when (breakdownPeriod) {
            AllTime -> breakdowns.allTime?.let { listOf(breakdowns.allTime.datePeriod) }
                ?: listOf()
            Yearly -> breakdowns.yearly.map { it.datePeriod }
            Monthly -> breakdowns.monthly.map { it.datePeriod }
        }
    }
    var selectedDatePeriod by remember(datePeriods) { mutableStateOf(datePeriods.first()) }

    LithiumTheme {
        ExpandedTopBarPreview { scrollBehavior ->
            BreakdownTopBar(
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
                scrollBehavior = scrollBehavior,
            )
        }
    }
}