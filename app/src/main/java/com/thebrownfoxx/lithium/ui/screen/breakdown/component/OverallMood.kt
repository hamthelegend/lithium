package com.thebrownfoxx.lithium.ui.screen.breakdown.component

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
import com.thebrownfoxx.lithium.ui.screen.breakdown.component.graph.Graph
import com.thebrownfoxx.lithium.ui.screen.breakdown.component.graph.MoodBar

@Composable
fun OverallMoodGraph(
    feelingCategoryPercents: FeelingCategoryPercents,
    modifier: Modifier = Modifier,
) {
    Graph(
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
fun GraphPreview() {
    val feelingCategoryPercents = remember { Sample.CheckIns.feelingCategoryPercents }

    Graph(
        label = "Overall mood",
        modifier = Modifier.padding(16.dp),
    ) {
        MoodBar(
            feelingCategoryPercents = feelingCategoryPercents,
            modifier = Modifier.height(256.dp)
        )
    }
}