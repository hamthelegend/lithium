package com.thebrownfoxx.lithium.ui.screen.breakdown

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thebrownfoxx.lithium.data.model.toCheckIn
import com.thebrownfoxx.lithium.data.repository.LithiumRepository
import com.thebrownfoxx.lithium.domain.CheckIn
import com.thebrownfoxx.lithium.domain.breakdown.toBreakdowns
import com.thebrownfoxx.lithium.ui.screen.breakdown.BreakdownPeriod.AllTime
import com.thebrownfoxx.lithium.ui.screen.breakdown.BreakdownPeriod.Monthly
import com.thebrownfoxx.lithium.ui.screen.breakdown.BreakdownPeriod.Yearly
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class BreakdownViewModel(lithiumRepository: LithiumRepository) : ViewModel() {
    val breakdowns = lithiumRepository.getAllCheckIns().map { checkInEntities ->
        checkInEntities.map { checkInEntity -> checkInEntity.toCheckIn() }.toBreakdowns()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, listOf<CheckIn>().toBreakdowns())

//    private val _breakdowns = MutableStateFlow(Sample.CheckIns.toBreakdowns())
//    val breakdowns = _breakdowns.asStateFlow()

    private var _breakdownPeriod = MutableStateFlow(AllTime)
    val breakdownPeriod = _breakdownPeriod.asStateFlow()

    private val breakdownsByPeriod = combine(
        breakdowns,
        breakdownPeriod,
    ) { breakdowns, breakdownPeriod ->
        when (breakdownPeriod) {
            AllTime -> listOf(breakdowns.allTime)
            Yearly -> breakdowns.yearly
            Monthly -> breakdowns.monthly
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, listOf())

    val datePeriods = breakdownsByPeriod.map { breakdownsByPeriod ->
        breakdownsByPeriod.map { breakdown -> breakdown?.datePeriod }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, listOf())

    private val _selectedDatePeriod = MutableStateFlow(datePeriods.value.firstOrNull())
    val selectedDatePeriod = _selectedDatePeriod.asStateFlow()

    init {
        viewModelScope.launch {
            datePeriods.collect { datePeriods ->
                _selectedDatePeriod.update { datePeriods.firstOrNull() }
            }
        }
    }

    val breakdown = combine(
        breakdownsByPeriod,
        selectedDatePeriod
    ) { breakdownsByPeriod, selectedDatePeriod ->
        breakdownsByPeriod.firstOrNull { it?.datePeriod == selectedDatePeriod }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, breakdowns.value.allTime)

    fun onToggleBreakdownPeriod() {
        _breakdownPeriod.update {
            when (it) {
                AllTime -> Yearly
                Yearly -> Monthly
                Monthly -> AllTime
            }
        }
    }

    fun onSelectedDatePeriodChange(newSelectedDatePeriod: OpenEndRange<LocalDate>) {
        _selectedDatePeriod.update { newSelectedDatePeriod }
    }
}