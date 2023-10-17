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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.R
import com.thebrownfoxx.lithium.domain.FeelingCategory
import com.thebrownfoxx.lithium.domain.FeelingCategory.*
import com.thebrownfoxx.lithium.ui.component.Elevation
import com.thebrownfoxx.lithium.ui.component.ExpandedTopAppBar
import com.thebrownfoxx.lithium.ui.component.plus
import com.thebrownfoxx.lithium.ui.extension.color
import com.thebrownfoxx.lithium.ui.theme.LithiumIcons
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    feelingCategoriesToday: List<FeelingCategory>,
    showUndoButton: Boolean,
    onUndoDeleteCheckIn: () -> Unit,
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
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
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
        Text(text = stringResource(R.string.app_name))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BlankHomeTopBarPreview() {
    LithiumTheme {
        val scrollBehavior =
            TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                HomeTopBar(
                    feelingCategoriesToday = listOf(),
                    scrollBehavior = scrollBehavior,
                    showUndoButton = true,
                    onUndoDeleteCheckIn = {},
                )
            }
        ) { contentPadding ->
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = contentPadding + PaddingValues(16.dp) +
                        WindowInsets.navigationBars.asPaddingValues(),
            ) {
                items(1000) {
                    Text(text = it.toString())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FilledHomeTopBarPreview() {
    LithiumTheme {
        val scrollBehavior =
            TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
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
                )
            }
        ) { contentPadding ->
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = contentPadding + PaddingValues(16.dp) +
                        WindowInsets.navigationBars.asPaddingValues(),
            ) {
                items(1000) {
                    Text(text = it.toString())
                }
            }
        }
    }
}