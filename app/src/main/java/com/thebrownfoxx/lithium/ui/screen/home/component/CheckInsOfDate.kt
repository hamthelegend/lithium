package com.thebrownfoxx.lithium.ui.screen.home.component

import com.thebrownfoxx.lithium.domain.CheckIn
import java.time.LocalDate
import java.time.ZoneId

data class CheckInsOfDate(
    val date: LocalDate,
    val checkIns: List<CheckIn>,
)

fun List<CheckIn>.toCheckInsByDate() =
    groupBy { it.instant.atZone(ZoneId.systemDefault()).toLocalDate()!! }
        .map { (date, checkIns) -> CheckInsOfDate(date, checkIns) }

fun List<CheckIn>.toFeelingCategoriesToday() =
    filter { it.instant.atZone(ZoneId.systemDefault()).toLocalDate() == LocalDate.now() }
        .groupBy { it.feeling.category }
        .keys