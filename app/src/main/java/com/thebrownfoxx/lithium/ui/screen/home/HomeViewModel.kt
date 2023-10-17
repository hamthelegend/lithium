package com.thebrownfoxx.lithium.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thebrownfoxx.lithium.domain.Sample
import com.thebrownfoxx.lithium.ui.screen.home.components.toCheckInsByDate
import com.thebrownfoxx.lithium.ui.screen.home.components.toFeelingCategoriesToday
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel: ViewModel() {
    private val checkIns = MutableStateFlow(Sample.CheckIns)

    val checkInsByDate = checkIns.map { it.toCheckInsByDate() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, listOf())

    val feelingCategoriesToday = checkIns.map { it.toFeelingCategoriesToday() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, setOf())
}