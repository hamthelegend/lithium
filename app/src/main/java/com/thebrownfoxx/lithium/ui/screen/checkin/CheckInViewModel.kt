package com.thebrownfoxx.lithium.ui.screen.checkin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.thebrownfoxx.lithium.LithiumApplication
import com.thebrownfoxx.lithium.data.model.toCheckInEntity
import com.thebrownfoxx.lithium.data.repository.LithiumRepository
import com.thebrownfoxx.lithium.domain.CheckIn
import com.thebrownfoxx.lithium.domain.Feeling
import com.thebrownfoxx.lithium.domain.FeelingCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant

class CheckInViewModel(
    private val lithiumRepository: LithiumRepository,
    application: Application,
) : AndroidViewModel(application) {
    private val _feelingCategory = MutableStateFlow<FeelingCategory?>(null)
    val feelingCategory = _feelingCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val feelings = combine(feelingCategory, searchQuery) { feelingCategory, searchQuery ->
        Feeling.entries.filter {
            searchQuery.lowercase() in
                    getApplication<LithiumApplication>().resources.getString(it.titleResourceId).lowercase()
                    && (feelingCategory == it.category || feelingCategory == null)
        }.sortedBy {
            getApplication<LithiumApplication>().resources.getString(it.titleResourceId).lowercase()
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, listOf())

    fun onFeelingCategoryChange(newCategory: FeelingCategory) {
        _feelingCategory.update { if (newCategory == it) null else newCategory }
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.update { newQuery }
    }

    fun onCheckIn(feeling: Feeling) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val checkIn = CheckIn(
                    feeling = feeling,
                    instant = Instant.now(),
                )
                lithiumRepository.add(checkIn.toCheckInEntity())
            }
        }
    }
}