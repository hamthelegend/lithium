package com.thebrownfoxx.lithium.ui.screen.breakdown.component.graph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.domain.FeelingCategory.*
import com.thebrownfoxx.lithium.domain.breakdown.FeelingCategoryPercents
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme

@Composable
fun MoodBar(
    feelingCategoryPercents: FeelingCategoryPercents,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            for (feelingCategory in entries) {
                val percent = feelingCategoryPercents[feelingCategory] ?: 0
                var showPercentLabel by remember { mutableStateOf(true) }
                
                LithiumTheme(feelingCategory = feelingCategory) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(percent.toFloat()),
                    ) {
                        Box {
                            if (showPercentLabel) {
                                Surface(
                                    shape = CircleShape,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.align(Alignment.Center),
                                ) {
                                    Text(
                                        text = "$percent%",
                                        fontWeight = FontWeight.ExtraBold,
                                        onTextLayout = { layoutResult ->
                                            if (layoutResult.didOverflowHeight)
                                                showPercentLabel = false
                                        },
                                        modifier = Modifier.padding(
                                            horizontal = 8.dp,
                                            vertical = 4.dp,
                                        ),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MoodBarPreview() {
    val feelingCategoryPercents = remember {
        mapOf(
            HighEnergyPleasant to 20,
            HighEnergyUnpleasant to 30,
            LowEnergyPleasant to 45,
            LowEnergyUnpleasant to 5,
        )
    }

    LithiumTheme {
        MoodBar(
            feelingCategoryPercents = feelingCategoryPercents,
            modifier = Modifier
                .padding(16.dp)
                .height(256.dp)
        )
    }
}