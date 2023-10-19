package com.thebrownfoxx.lithium.ui.screen.breakdown.component.graph

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.R
import com.thebrownfoxx.lithium.domain.Sample
import com.thebrownfoxx.lithium.domain.breakdown.FeelingCategoryPercents
import com.thebrownfoxx.lithium.domain.breakdown.feelingCategoryPercents
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme

@Composable
fun OverallMoodCard(
    feelingCategoryPercents: FeelingCategoryPercents,
    modifier: Modifier = Modifier,
) {
    GraphCard(
        label = stringResource(R.string.overall_mood),
        modifier = modifier,
    ) {
        MoodBar(
            feelingCategoryPercents = feelingCategoryPercents,
            modifier = Modifier.height(256.dp)
        )
    }
}

@Preview
@Composable
fun OverallMoodCardPreview() {
    val feelingCategoryPercents = remember { Sample.CheckIns.feelingCategoryPercents }

    LithiumTheme {
        OverallMoodCard(
            feelingCategoryPercents = feelingCategoryPercents,
            modifier = Modifier.padding(16.dp),
        )
    }
}