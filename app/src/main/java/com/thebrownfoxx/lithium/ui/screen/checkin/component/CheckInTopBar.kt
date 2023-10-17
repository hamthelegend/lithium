package com.thebrownfoxx.lithium.ui.screen.checkin.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.R
import com.thebrownfoxx.lithium.domain.FeelingCategory
import com.thebrownfoxx.lithium.domain.FeelingCategory.*
import com.thebrownfoxx.lithium.ui.component.ExpandedTopAppBar
import com.thebrownfoxx.lithium.ui.component.plus
import com.thebrownfoxx.lithium.ui.theme.LithiumIcons
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckInTopBar(
    feelingCategory: FeelingCategory?,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    val text = when (feelingCategory) {
        null -> stringResource(R.string.check_in)
        HighEnergyPleasant -> stringResource(R.string.high_energy_pleasant)
        HighEnergyUnpleasant -> stringResource(R.string.high_energy_unpleasant)
        LowEnergyPleasant -> stringResource(R.string.low_energy_pleasant)
        LowEnergyUnpleasant -> stringResource(R.string.low_energy_unpleasant)
    }

    ExpandedTopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        pinnedContent = {
            TextField(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = { Text(stringResource(R.string.search)) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                ),
                leadingIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = LithiumIcons.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                        )
                    }
                },
                trailingIcon = {
                    if (searchQuery != "") {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(
                                imageVector = LithiumIcons.Clear,
                                contentDescription = stringResource(R.string.clear),
                            )
                        }
                    }
                }
            )
        },
        pinCollapsedContent = true,
    ) {
        AnimatedContent(targetState = text) { text ->
            Text(
                text = text,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CheckInTopBarPreview() {
    var query by remember { mutableStateOf("") }

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    LithiumTheme {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CheckInTopBar(
                    feelingCategory = null,
                    searchQuery = query,
                    onSearchQueryChange = { query = it },
                    scrollBehavior = scrollBehavior,
                    onNavigateUp = {},
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