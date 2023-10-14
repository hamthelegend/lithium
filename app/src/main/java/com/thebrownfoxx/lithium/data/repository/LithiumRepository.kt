package com.thebrownfoxx.lithium.data.repository

import com.thebrownfoxx.lithium.data.model.CheckInEntity
import kotlinx.coroutines.flow.Flow

interface LithiumRepository {
    suspend fun add(checkInEntity: CheckInEntity)

    suspend fun delete(checkInEntity: CheckInEntity)

    fun getCheckIn(id: Int): Flow<CheckInEntity?>

    fun getAllCheckIns(): Flow<List<CheckInEntity>>
}