package com.thebrownfoxx.lithium.ui.screen.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material.icons.twotone.NewReleases
import androidx.compose.material.icons.twotone.Remove
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material.icons.twotone.Warning
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
import com.thebrownfoxx.lithium.domain.FeelingCategory.*
import com.thebrownfoxx.lithium.ui.component.HorizontalSpacer
import com.thebrownfoxx.lithium.ui.extension.TimeFormatter
import com.thebrownfoxx.lithium.ui.theme.LithiumIcons
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme
import java.time.Instant

@Composable
fun CheckInCard(
    checkIn: CheckIn,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val icon = when (checkIn.feeling.category) {
        HighEnergyPleasant -> LithiumIcons.Star
        HighEnergyUnpleasant -> LithiumIcons.NewReleases
        LowEnergyPleasant -> LithiumIcons.Warning
        LowEnergyUnpleasant -> LithiumIcons.CheckCircle
    }

    val iconContentDescription = when (checkIn.feeling.category) {
        HighEnergyPleasant -> stringResource(R.string.high_energy_pleasant)
        HighEnergyUnpleasant -> stringResource(R.string.high_energy_unpleasant)
        LowEnergyPleasant -> stringResource(R.string.low_energy_pleasant)
        LowEnergyUnpleasant -> stringResource(R.string.low_energy_unpleasant)
    }

    LithiumTheme(feelingCategory = checkIn.feeling.category) {
        Card(modifier = modifier) {
            Row(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(modifier = Modifier.size(48.dp)) {
                    Icon(
                        imageVector = icon,
                        contentDescription = iconContentDescription,
                        modifier = Modifier.align(Alignment.Center),
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