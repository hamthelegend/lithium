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
import kotlinx.coroutines.flow.asStateFlow
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

    private val _checkInToDelete = MutableStateFlow<CheckIn?>(null)
    val checkInToDelete = _checkInToDelete.asStateFlow()

    fun onDeleteCheckIn(checkIn: CheckIn) {
        _checkInToDelete.update { checkIn }
    }

    fun onCommitDeleteCheckIn() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                checkInToDelete.value?.let { checkInToDelete ->
                    lithiumRepository.delete(checkInToDelete.toCheckInEntity())
                    _checkInToDelete.update { null }
                }
            }
        }
    }

    fun onCancelDeleteCheckIn() {
        _checkInToDelete.update { null }
    }
}