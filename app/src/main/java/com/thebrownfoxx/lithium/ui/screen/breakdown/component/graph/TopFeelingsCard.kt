package com.thebrownfoxx.lithium.ui.screen.breakdown.component.graph

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.R
import com.thebrownfoxx.lithium.domain.Sample
import com.thebrownfoxx.lithium.domain.breakdown.FeelingFrequencies
import com.thebrownfoxx.lithium.domain.breakdown.toBreakdowns
import com.thebrownfoxx.lithium.ui.theme.LithiumIcons
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
            Card {
                Box(
                    modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                        .fillMaxWidth(),
                ) {
                    Icon(
                        imageVector = LithiumIcons.MoreHoriz,
                        contentDescription = stringResource(R.string.more),
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.Center),
                    )
                }
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