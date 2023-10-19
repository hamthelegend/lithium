package com.thebrownfoxx.lithium.ui.screen.breakdown.component.graph

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.R
import com.thebrownfoxx.lithium.domain.Sample
import com.thebrownfoxx.lithium.domain.breakdown.WeeklyMood
import com.thebrownfoxx.lithium.domain.breakdown.toBreakdowns
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme
import java.time.DayOfWeek

@Composable
fun WeeklyMoodCard(
    weeklyMood: WeeklyMood,
    modifier: Modifier = Modifier,
) {
    GraphCard(
        label = stringResource(R.string.weekly_mood),
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            for (dayOfWeek in DayOfWeek.values()) {
                val text = when (dayOfWeek) {
                    DayOfWeek.MONDAY -> stringResource(R.string.monday_symbol)
                    DayOfWeek.TUESDAY -> stringResource(R.string.tuesday_symbol)
                    DayOfWeek.WEDNESDAY -> stringResource(R.string.wednesday_symbol)
                    DayOfWeek.THURSDAY -> stringResource(R.string.thursday_symbol)
                    DayOfWeek.FRIDAY -> stringResource(R.string.friday_symbol)
                    DayOfWeek.SATURDAY -> stringResource(R.string.saturday_symbol)
                    DayOfWeek.SUNDAY -> stringResource(R.string.sunday_symbol)
                }
                val feelingCategoryPercents = weeklyMood[dayOfWeek] ?: mapOf()

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    MoodBar(
                        feelingCategoryPercents = feelingCategoryPercents,
                        modifier = Modifier
                            .height(256.dp)
                            .fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun WeeklyMoodCardPreview() {
    val weeklyMood = remember { Sample.CheckIns.toBreakdowns().allTime!!.weeklyMood }

    LithiumTheme {
        WeeklyMoodCard(
            weeklyMood = weeklyMood,
            modifier = Modifier.padding(16.dp),
        )
    }
}