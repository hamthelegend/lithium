package com.thebrownfoxx.lithium.ui.screen.home.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.thebrownfoxx.lithium.R
import com.thebrownfoxx.lithium.domain.FeelingCategory
import com.thebrownfoxx.lithium.domain.FeelingCategory.*
import com.thebrownfoxx.lithium.ui.component.Elevation
import com.thebrownfoxx.lithium.ui.component.ExpandedTopAppBar
import com.thebrownfoxx.lithium.ui.extension.ExpandedTopBarPreview
import com.thebrownfoxx.lithium.ui.extension.color
import com.thebrownfoxx.lithium.ui.theme.LithiumIcons
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    feelingCategoriesToday: List<FeelingCategory>,
    showUndoButton: Boolean,
    onUndoDeleteCheckIn: () -> Unit,
    onShowBreakdown: () -> Unit,
    showBreakdownButton: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    val emptyColor = MaterialTheme.colorScheme.surfaceColorAtElevation(Elevation.level(1))
    val containerColor =
        when (feelingCategoriesToday.size) {
            0 -> SolidColor(emptyColor)
            1 -> SolidColor(feelingCategoriesToday.single().color)
            else -> {
                val stops = feelingCategoriesToday.map { it.color }
                Brush.linearGradient(stops)
            }
        }

    ExpandedTopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        collapsedContent = {
            Text(
                text = stringResource(R.string.app_name),
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        navigationIcon = {
            AnimatedVisibility(
                visible = showBreakdownButton,
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut(),
            ) {
                IconButton(onClick = onShowBreakdown) {
                    Icon(
                        imageVector = LithiumIcons.BarChart,
                        contentDescription = stringResource(R.string.breakdown)
                    )
                }
            }
        },
        background = {
            AnimatedContent(
                targetState = containerColor,
                transitionSpec = { fadeIn() togetherWith fadeOut() }
            ) { containerColor ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(containerColor),
                )
            }
        },
        actions = {
            AnimatedVisibility(
                visible = showUndoButton,
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut(),
            ) {
                IconButton(onClick = onUndoDeleteCheckIn) {
                    Icon(
                        imageVector = LithiumIcons.Undo,
                        contentDescription = stringResource(R.string.undo),
                    )
                }
            }
        }
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BlankHomeTopBarPreview() {
    LithiumTheme {
        ExpandedTopBarPreview { scrollBehavior ->
            HomeTopBar(
                feelingCategoriesToday = listOf(),
                scrollBehavior = scrollBehavior,
                showUndoButton = true,
                onUndoDeleteCheckIn = {},
                showBreakdownButton = false,
                onShowBreakdown = {},
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FilledHomeTopBarPreview() {
    LithiumTheme {
        ExpandedTopBarPreview { scrollBehavior ->
            HomeTopBar(
                feelingCategoriesToday = listOf(
                    HighEnergyPleasant,
                    HighEnergyUnpleasant,
                    LowEnergyPleasant,
                    LowEnergyUnpleasant,
                ),
                scrollBehavior = scrollBehavior,
                showUndoButton = true,
                onUndoDeleteCheckIn = {},
                showBreakdownButton = false,
                onShowBreakdown = {},
            )
        }
    }
}