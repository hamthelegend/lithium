package com.thebrownfoxx.lithium.ui.screen.home

import androidx.compose.material.icons.twotone.DriveFileRenameOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.thebrownfoxx.lithium.R
import com.thebrownfoxx.lithium.domain.CheckIn
import com.thebrownfoxx.lithium.domain.FeelingCategory
import com.thebrownfoxx.lithium.domain.Sample
import com.thebrownfoxx.lithium.ui.extension.floatingActionButtonExpanded
import com.thebrownfoxx.lithium.ui.screen.home.components.CheckInTopBar
import com.thebrownfoxx.lithium.ui.screen.home.components.CheckInsList
import com.thebrownfoxx.lithium.ui.screen.home.components.CheckInsOfDate
import com.thebrownfoxx.lithium.ui.screen.home.components.toCheckInsByDate
import com.thebrownfoxx.lithium.ui.screen.home.components.toFeelingCategoriesToday
import com.thebrownfoxx.lithium.ui.theme.LithiumIcons
import com.thebrownfoxx.lithium.ui.theme.LithiumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    checkInsByDate: List<CheckInsOfDate>,
    feelingCategoriesToday: Set<FeelingCategory>,
    onCheckIn: () -> Unit,
    onDeleteCheckIn: (CheckIn) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CheckInTopBar(
                feelingCategoriesToday = feelingCategoriesToday,
                scrollBehavior = scrollBehavior,
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
            modifier = modifier,
            contentPadding = contentPadding,
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val checkIns = remember { Sample.CheckIns.toCheckInsByDate() }
    val feelingCategories = remember { Sample.CheckIns.toFeelingCategoriesToday() }

    LithiumTheme {
        HomeScreen(
            checkInsByDate = checkIns,
            feelingCategoriesToday = feelingCategories,
            onCheckIn = {},
            onDeleteCheckIn = {},
        )
    }
}