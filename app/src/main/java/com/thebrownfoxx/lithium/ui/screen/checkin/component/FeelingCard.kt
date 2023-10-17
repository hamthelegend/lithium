package com.thebrownfoxx.lithium.ui.screen.checkin.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.thebrownfoxx.lithium.ui.extension.icon
import com.thebrownfoxx.lithium.ui.extension.iconContentDescription
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeelingCard(
    feeling: Feeling,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LithiumTheme(feelingCategory = feeling.category) {
        Card(
            modifier = modifier,
            onClick = onClick,
        ) {
            Row(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(modifier = Modifier.size(48.dp)) {
                    Icon(
                        imageVector = feeling.category.icon,
                        contentDescription = feeling.category.iconContentDescription,
                        modifier = Modifier.align(Alignment.Center),
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
                HorizontalSpacer(width = 4.dp)
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = feeling.title,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = feeling.description,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FeelingCardPreview() {
    LithiumTheme {
        FeelingCard(feeling = Feeling.Agitated, onClick = {}, modifier = Modifier.padding(16.dp))
    }
}