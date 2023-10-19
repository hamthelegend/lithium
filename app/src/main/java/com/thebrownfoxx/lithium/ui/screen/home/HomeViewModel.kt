package com.thebrownfoxx.lithium.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thebrownfoxx.lithium.data.model.toCheckIn
import com.thebrownfoxx.lithium.data.model.toCheckInEntity
import com.thebrownfoxx.lithium.data.repository.LithiumRepository
import com.thebrownfoxx.lithium.domain.CheckIn
import com.thebrownfoxx.lithium.ui.screen.home.component.toCheckInsByDate
import com.thebrownfoxx.lithium.ui.screen.home.component.toFeelingCategoriesToday
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val lithiumRepository: LithiumRepository): ViewModel() {
    private val checkIns = lithiumRepository.getAllCheckIns()
        .map { it.map { it.toCheckIn() } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, listOf())

    val checkInsByDate = checkIns.map { it.toCheckInsByDate() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, listOf())

    val feelingCategoriesToday = checkIns.map { it.toFeelingCategoriesToday() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, listOf())

    private val deletedCheckIns = MutableStateFlow<List<CheckIn>>(listOf())

    val showUndoButton = deletedCheckIns.map { it.isNotEmpty() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val showBreakdownButton = checkIns.map { it.isNotEmpty() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun onDeleteCheckIn(checkIn: CheckIn) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                lithiumRepository.delete(checkIn.toCheckInEntity())
                deletedCheckIns.update { it + checkIn.copy(id = null) }
            }
        }
    }

    fun onUndoDeleteCheckIn() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deletedCheckIns.value.lastOrNull()?.let { deletedCheckIn ->
                    deletedCheckIns.update { it.dropLast(1) }
                    lithiumRepository.add(deletedCheckIn.toCheckInEntity())
                }
            }
        }
    }
}