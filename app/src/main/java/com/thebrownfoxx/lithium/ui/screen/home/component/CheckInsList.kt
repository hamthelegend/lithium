package com.thebrownfoxx.lithium.ui.screen.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.domain.CheckIn
import com.thebrownfoxx.lithium.ui.component.plus
import com.thebrownfoxx.lithium.ui.extension.DateFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CheckInsList(
    checkInsByDate: List<CheckInsOfDate>,
    onDeleteCheckIn: (CheckIn) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = contentPadding + PaddingValues(16.dp) ,
    ) {
        for ((date, checkInsOfDate) in checkInsByDate.map { (key, value) -> key to value }) {
            item {
                Text(
                    text = DateFormatter.format(date),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            items(
                items = checkInsOfDate,
                key = { it.id ?: -1 },
            ) { checkIn ->
                CheckInCard(
                    checkIn = checkIn,
                    onDelete = { onDeleteCheckIn(checkIn) },
                    modifier = Modifier.animateItemPlacement(),
                )
            }
        }
    }
}