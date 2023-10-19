package com.thebrownfoxx.lithium.ui.screen.breakdown.component.graph

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.R
import com.thebrownfoxx.lithium.domain.Sample
import com.thebrownfoxx.lithium.domain.breakdown.FeelingFrequencies
import com.thebrownfoxx.lithium.domain.breakdown.toBreakdowns
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme

@Composable
fun TopFeelingsCard(
    topFeelings: FeelingFrequencies,
    modifier: Modifier = Modifier,
) {
    GraphCard(
        label = stringResource(R.string.top_feelings),
        modifier = modifier,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            for ((feeling, frequency) in topFeelings.entries) {
                FeelingFrequencyCard(feeling = feeling, frequency = frequency)
            }
        }
    }
}

@Preview
@Composable
fun TopFeelingsCardPreview() {
    val topFeelings = remember { Sample.CheckIns.toBreakdowns().allTime!!.topFeelings }

    LithiumTheme {
        TopFeelingsCard(
            topFeelings = topFeelings,
            modifier = Modifier.padding(16.dp),
        )
    }
}