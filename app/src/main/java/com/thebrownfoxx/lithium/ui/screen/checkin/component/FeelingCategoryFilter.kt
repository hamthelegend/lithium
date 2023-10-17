package com.thebrownfoxx.lithium.ui.screen.checkin.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.domain.FeelingCategory
import com.thebrownfoxx.lithium.ui.component.Elevation
import com.thebrownfoxx.lithium.ui.extension.icon
import com.thebrownfoxx.lithium.ui.extension.iconContentDescription
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeelingCategoryFilter(
    selectedFeelingCategory: FeelingCategory?,
    onSelectedFeelingCategoryChange: (FeelingCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        for (category in FeelingCategory.entries) {
            LithiumTheme(feelingCategory = category) {
                val color by animateColorAsState(
                    if (selectedFeelingCategory == category) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.primaryContainer
                )
                Surface(
                    shape = CircleShape,
                    color = color,
                    shadowElevation = Elevation.level(3),
                    onClick = { onSelectedFeelingCategoryChange(category) },
                ) {
                    Box(modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = category.icon,
                            contentDescription = category.iconContentDescription,
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FeelingCategoryFilterPreview() {
    var category: FeelingCategory? by remember { mutableStateOf(null) }

    LithiumTheme {
        FeelingCategoryFilter(
            selectedFeelingCategory = category,
            onSelectedFeelingCategoryChange = {
                category = if (category == it) null else it
            },
            modifier = Modifier.padding(16.dp),
        )
    }
}