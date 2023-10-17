package com.thebrownfoxx.lithium.ui.extension

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
val TopAppBarScrollBehavior.floatingActionButtonExpanded: Boolean
    @Composable get() {
        var previousOffset by remember { mutableFloatStateOf(state.heightOffset + state.contentOffset - 1) }
        return remember {
            derivedStateOf {
                (state.heightOffset + state.contentOffset > previousOffset).also {
                    previousOffset = state.heightOffset + state.contentOffset
                }
            }
        }.value
    }