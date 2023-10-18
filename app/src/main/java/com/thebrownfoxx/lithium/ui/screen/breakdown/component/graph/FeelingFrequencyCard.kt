package com.thebrownfoxx.lithium.ui.screen.breakdown.component.graph

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.domain.Feeling
import com.thebrownfoxx.lithium.ui.component.HorizontalSpacer
import com.thebrownfoxx.lithium.ui.component.WeightedSpacer
import com.thebrownfoxx.lithium.ui.extension.icon
import com.thebrownfoxx.lithium.ui.extension.iconContentDescription
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme

@Composable
fun FeelingFrequencyCard(
    feeling: Feeling,
    frequency: Int,
    modifier: Modifier = Modifier,
) {
    LithiumTheme(feelingCategory = feeling.category) {
        Card(modifier = modifier) {
            Row(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                    Icon(
                        imageVector = feeling.category.icon,
                        contentDescription = feeling.category.iconContentDescription,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp),
                    )
                HorizontalSpacer(width = 16.dp)
                Text(
                    text = feeling.title,
                    style = MaterialTheme.typography.titleSmall,
                )
                WeightedSpacer(weight = 1f)
                Text(
                    text = frequency.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                )
            }
        }
    }
}

@Preview
@Composable
fun FeelingFrequencyPreview() {
    LithiumTheme {
        FeelingFrequencyCard(
            feeling = Feeling.Agitated,
            frequency = 10,
            modifier = Modifier.padding(16.dp),
        )
    }
}