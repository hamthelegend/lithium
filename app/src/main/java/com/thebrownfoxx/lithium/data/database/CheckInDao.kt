package com.thebrownfoxx.lithium.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.thebrownfoxx.lithium.data.model.CheckInEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckInDao {
    @Insert
    suspend fun insert(checkInEntity: CheckInEntity)

    @Delete
    suspend fun delete(checkInEntity: CheckInEntity)

    @Query("SELECT * FROM check_in WHERE id = :id LIMIT 1")
    fun get(id: Int): Flow<CheckInEntity?>

    @Query("SELECT * FROM check_in ORDER BY instantEpochMilli DESC")
    fun getAll(): Flow<List<CheckInEntity>>
}