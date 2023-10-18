package com.thebrownfoxx.lithium.ui.screen.breakdown

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreakdownScreen(
    modifier: Modifier = Modifier,
) {
    /*val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopBar(
                feelingCategoriesToday = feelingCategoriesToday,
                scrollBehavior = scrollBehavior,
                showUndoButton = showUndoButton,
                onUndoDeleteCheckIn = onUndoDeleteCheckIn,
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(R.string.check_in)) },
                icon = {
                    Icon(
                        imageVector = LithiumIcons.DriveFileRenameOutline,
                        contentDescription = stringResource(R.string.check_in),
                    )
                },
                onClick = onCheckIn,
                expanded = scrollBehavior.floatingActionButtonExpanded,
            )
        },
    ) { contentPadding ->
        CheckInsList(
            checkInsByDate = checkInsByDate,
            onDeleteCheckIn = onDeleteCheckIn,
            modifier = Modifier.fillMaxSize(),
            contentPadding = contentPadding + PaddingValues(16.dp),
        )
    }*/
}


@Preview
@Composable
fun BreakdownScreenPreview() {
    LithiumTheme {
        BreakdownScreen()
    }
}