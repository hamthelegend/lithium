package com.thebrownfoxx.lithium.ui.screen.breakdown.component.graph

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.domain.Sample
import com.thebrownfoxx.lithium.domain.breakdown.feelingCategoryPercents
import com.thebrownfoxx.lithium.ui.component.Elevation
import com.thebrownfoxx.lithium.ui.component.VerticalSpacer

@Composable
fun GraphCard(
    label: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceColorAtElevation(Elevation.level(1)),
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth(),
            )
            VerticalSpacer(height = 16.dp)
            content()
        }
    }
}

@Preview
@Composable
fun GraphPreview() {
    val feelingCategoryPercents = remember { Sample.CheckIns.feelingCategoryPercents }

    GraphCard(
        label = "Overall mood",
        modifier = Modifier.padding(16.dp),
    ) {
        MoodBar(
            feelingCategoryPercents = feelingCategoryPercents,
            modifier = Modifier.height(256.dp)
        )
    }
}