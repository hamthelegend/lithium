package com.thebrownfoxx.lithium.ui.screen.breakdown.component.graph

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.domain.Sample
import com.thebrownfoxx.lithium.domain.breakdown.Breakdown
import com.thebrownfoxx.lithium.domain.breakdown.toBreakdowns
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme

@Composable
fun BreakdownCards(
    breakdown: Breakdown,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = contentPadding,
    ) {
        item {
            OverallMoodCard(feelingCategoryPercents = breakdown.overallMood)
        }
        item {
            TopFeelingsCard(topFeelings = breakdown.topFeelings)
        }
        item {
            DailyMoodCard(dailyMood = breakdown.dailyMood)
        }
        item {
            WeeklyMoodCard(weeklyMood = breakdown.weeklyMood)
        }
    }
}

@Preview
@Composable
fun BreakdownCardsPreview() {
    val breakdowns = remember { Sample.CheckIns.toBreakdowns().allTime }

    LithiumTheme {
        BreakdownCards(
            breakdown = breakdowns!!,
            contentPadding = PaddingValues(16.dp),
        )
    }
}