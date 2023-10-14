package com.thebrownfoxx.lithium.data.repository

import com.thebrownfoxx.lithium.data.database.CheckInDao
import com.thebrownfoxx.lithium.data.model.CheckInEntity
import kotlinx.coroutines.flow.Flow

class OfflineLithiumRepository(private val checkInDao: CheckInDao) : LithiumRepository {
    override suspend fun add(checkInEntity: CheckInEntity) {
        checkInDao.insert(checkInEntity)
    }

    override suspend fun delete(checkInEntity: CheckInEntity) {
        checkInDao.delete(checkInEntity)
    }

    override fun getCheckIn(id: Int): Flow<CheckInEntity?> {
        return checkInDao.get(id)
    }

    override fun getAllCheckIns(): Flow<List<CheckInEntity>> {
        return checkInDao.getAll()
    }

}