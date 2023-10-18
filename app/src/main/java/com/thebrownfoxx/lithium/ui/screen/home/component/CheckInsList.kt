package com.thebrownfoxx.lithium.ui.screen.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.domain.CheckIn
import com.thebrownfoxx.lithium.ui.component.bottom
import com.thebrownfoxx.lithium.ui.component.end
import com.thebrownfoxx.lithium.ui.component.start
import com.thebrownfoxx.lithium.ui.component.top
import com.thebrownfoxx.lithium.ui.extension.DateFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CheckInsList(
    checkInsByDate: List<CheckInsOfDate>,
    onDeleteCheckIn: (CheckIn) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    val verticalContentPadding = PaddingValues(
        top = contentPadding.top,
        bottom = contentPadding.bottom,
    )
    val horizontalContentPadding = PaddingValues(
        start = contentPadding.start,
        end = contentPadding.end,
    )

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = verticalContentPadding,
    ) {
        for ((date, checkInsOfDate) in checkInsByDate.map { (key, value) -> key to value }) {
            item {
                Text(
                    text = DateFormatter.format(date),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontalContentPadding),
                )
            }
            items(
                items = checkInsOfDate,
                key = { it.id ?: -1 },
            ) { checkIn ->
                SwipeableCheckInCard(
                    checkIn = checkIn,
                    onSwipe = { onDeleteCheckIn(checkIn) },
                    modifier = Modifier.animateItemPlacement(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                )
            }
        }
    }
}