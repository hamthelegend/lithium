package com.thebrownfoxx.lithium.ui.screen.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.R
import com.thebrownfoxx.lithium.domain.CheckIn
import com.thebrownfoxx.lithium.domain.Feeling
import com.thebrownfoxx.lithium.ui.component.HorizontalSpacer
import com.thebrownfoxx.lithium.ui.extension.TimeFormatter
import com.thebrownfoxx.lithium.ui.extension.icon
import com.thebrownfoxx.lithium.ui.extension.iconContentDescription
import com.thebrownfoxx.lithium.ui.theme.LithiumIcons
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme
import java.time.Instant

@Composable
fun CheckInCard(
    checkIn: CheckIn,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LithiumTheme(feelingCategory = checkIn.feeling.category) {
        Card(modifier = modifier) {
            Row(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(modifier = Modifier.size(48.dp)) {
                    Icon(
                        imageVector = checkIn.feeling.category.icon,
                        contentDescription = checkIn.feeling.category.iconContentDescription,
                        modifier = Modifier.align(Alignment.Center),
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
                HorizontalSpacer(width = 4.dp)
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = checkIn.feeling.title,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = TimeFormatter.format(checkIn.instant),
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = LithiumIcons.Remove,
                        contentDescription = stringResource(R.string.delete),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CheckInPreview() {
    LithiumTheme {
        CheckInCard(
            checkIn = CheckIn(feeling = Feeling.Agitated, instant = Instant.now()),
            onDelete = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}