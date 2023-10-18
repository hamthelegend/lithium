package com.thebrownfoxx.lithium.ui.screen.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.DismissValue.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.domain.CheckIn
import com.thebrownfoxx.lithium.domain.Feeling
import com.thebrownfoxx.lithium.ui.component.HorizontalSpacer
import com.thebrownfoxx.lithium.ui.extension.TimeFormatter
import com.thebrownfoxx.lithium.ui.extension.icon
import com.thebrownfoxx.lithium.ui.extension.iconContentDescription
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeableCheckInCard(
    checkIn: CheckIn,
    onSwipe: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    val dismissState = rememberDismissState(
        confirmValueChange = { dismissValue ->
            val dismissed = dismissValue == DismissedToStart || dismissValue == DismissedToEnd
            if (dismissed) {
                onSwipe()
            }
            dismissed
        }
    )
    
    LithiumTheme(feelingCategory = checkIn.feeling.category) {
        SwipeToDismiss(
            modifier = modifier,
            state = dismissState,
            background = {},
            dismissContent = {
                Card(modifier = Modifier.padding(contentPadding)) {
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
                    }
                }
            },
        )
    }
}

@Preview
@Composable
fun SwipeableCheckInPreview() {
    LithiumTheme {
        SwipeableCheckInCard(
            checkIn = CheckIn(feeling = Feeling.Agitated, instant = Instant.now()),
            onSwipe = {},
            contentPadding = PaddingValues(16.dp),
        )
    }
}