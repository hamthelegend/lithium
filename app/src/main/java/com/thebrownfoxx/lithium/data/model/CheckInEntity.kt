package com.thebrownfoxx.lithium.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thebrownfoxx.lithium.domain.CheckIn
import com.thebrownfoxx.lithium.domain.Feeling
import java.time.Instant

@Entity(tableName = "check_in")
data class CheckInEntity(
    @PrimaryKey val id: Int? = null,
    val feelingId: Int,
    val instantEpochMilli: Long,
)

fun CheckIn.toCheckInEntity() = CheckInEntity(
    id = id,
    feelingId = feeling.id,
    instantEpochMilli = instant.toEpochMilli(),
)

fun CheckInEntity.toCheckIn() = CheckIn(
    id = id,
    feeling = Feeling.ofId(feelingId),
    instant = Instant.ofEpochMilli(instantEpochMilli),
)