package com.thebrownfoxx.lithium.ui.screen.checkin.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
        modifier = modifier.width(64.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        for (category in FeelingCategory.entries) {
            val selected = selectedFeelingCategory == category
            LithiumTheme(feelingCategory = category) {
                val containerColor by animateColorAsState(
                    if (selected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondaryContainer
                )
                val contentColor by animateColorAsState(
                    if (selected) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
                )

                val containerSize by animateDpAsState(if (selected) 64.dp else 48.dp)
                val contentSize by animateDpAsState(if (selected) 32.dp else 24.dp)

                Surface(
                    shape = CircleShape,
                    color = containerColor,
                    contentColor = contentColor,
                    shadowElevation = Elevation.level(3),
                    onClick = { onSelectedFeelingCategoryChange(category) },
                ) {
                    Box(modifier = Modifier.size(containerSize)) {
                        Icon(
                            imageVector = category.icon,
                            contentDescription = category.iconContentDescription,
                            modifier = Modifier
                                .size(contentSize)
                                .align(Alignment.Center),
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