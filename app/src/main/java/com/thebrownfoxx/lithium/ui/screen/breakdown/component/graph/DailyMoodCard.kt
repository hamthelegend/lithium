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
import com.thebrownfoxx.lithium.domain.breakdown.DailyMood
import com.thebrownfoxx.lithium.domain.breakdown.DayPeriod
import com.thebrownfoxx.lithium.domain.breakdown.toBreakdowns
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme

@Composable
fun DailyMoodCard(
    dailyMood: DailyMood,
    modifier: Modifier = Modifier,
) {
    GraphCard(
        label = stringResource(R.string.daily_mood),
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            for (dayPeriod in DayPeriod.entries) {
                val text = when (dayPeriod) {
                    DayPeriod.Morning -> stringResource(R.string.morning)
                    DayPeriod.Afternoon -> stringResource(R.string.afternoon)
                    DayPeriod.Evening -> stringResource(R.string.evening)
                    DayPeriod.Night -> stringResource(R.string.night)
                }
                val feelingCategoryPercents = dailyMood[dayPeriod] ?: mapOf()

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
fun DailyMoodCardPreview() {
    val dailyMood = remember { Sample.CheckIns.toBreakdowns().allTime!!.dailyMood }

    LithiumTheme {
        DailyMoodCard(
            dailyMood = dailyMood,
            modifier = Modifier.padding(16.dp),
        )
    }
}