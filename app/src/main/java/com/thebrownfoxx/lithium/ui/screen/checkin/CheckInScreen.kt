package com.thebrownfoxx.lithium.ui.screen.checkin

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.lithium.domain.Feeling
import com.thebrownfoxx.lithium.domain.FeelingCategory
import com.thebrownfoxx.lithium.ui.component.plus
import com.thebrownfoxx.lithium.ui.screen.checkin.component.CheckInTopBar
import com.thebrownfoxx.lithium.ui.screen.checkin.component.FeelingCard
import com.thebrownfoxx.lithium.ui.screen.checkin.component.FeelingCategoryFilter
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CheckInScreen(
    feelings: List<Feeling>,
    onCheckIn: (Feeling) -> Unit,
    feelingCategory: FeelingCategory?,
    onFeelingCategoryChange: (FeelingCategory) -> Unit,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(feelings) {
        lazyListState.animateScrollToItem(0, 0)
    }

    LithiumTheme(feelingCategory = feelingCategory) {
        Scaffold(
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CheckInTopBar(
                    feelingCategory = feelingCategory,
                    searchQuery = searchQuery,
                    onSearchQueryChange = onSearchQueryChange,
                    onNavigateUp = onNavigateUp,
                    scrollBehavior = scrollBehavior,
                )
            },
            floatingActionButton = {
                FeelingCategoryFilter(
                    selectedFeelingCategory = feelingCategory,
                    onSelectedFeelingCategoryChange = onFeelingCategoryChange,
                )
            }
        ) { contentPadding ->
            Surface {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = lazyListState,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = contentPadding + PaddingValues(16.dp),
                ) {
                    items(
                        items = feelings,
                        key = { it.id },
                    ) { feeling ->
                        FeelingCard(
                            feeling = feeling,
                            onClick = {
                                onCheckIn(feeling)
                                onNavigateUp()
                            },
                            modifier = Modifier.animateItemPlacement(),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CheckInScreenPreview() {
    val context = LocalContext.current
    val feelings = remember {
        Feeling.entries.sortedBy { context.resources.getString(it.titleResourceId) }
    }
    var query by remember { mutableStateOf("") }

    CheckInScreen(
        feelings = feelings,
        onCheckIn = {},
        feelingCategory = null,
        onFeelingCategoryChange = {},
        searchQuery = query,
        onSearchQueryChange = { query = it },
        onNavigateUp = {},
    )
}