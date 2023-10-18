package com.thebrownfoxx.lithium.domain.breakdown

import com.thebrownfoxx.lithium.domain.CheckIn
import com.thebrownfoxx.lithium.extension.inBetween
import java.time.LocalDate
import kotlin.math.roundToInt

val List<CheckIn>.feelingCategoryPercents
    get() = groupBy { it.feeling.category }
        .mapValues { (it.value.size.toFloat() / size * 100).roundToInt() }

fun List<CheckIn>.toBreakdown(datePeriod: OpenEndRange<LocalDate>): Breakdown {
    val feelingCategoryPercents = feelingCategoryPercents

    val feelingFrequencies = groupBy { it.feeling }.mapValues { it.value.size }

    val topFeelings = feelingFrequencies.entries
        .sortedByDescending { it.value }
        .take(10)
        .associate { (key, value) -> key to value }

    val dailyMood = groupBy { checkIn ->
        DayPeriod.entries.first { dayPeriod ->
            checkIn.localDateTime.toLocalTime() inBetween dayPeriod.range
        }
    }.mapValues { it.value.feelingCategoryPercents }

    val weeklyMood = groupBy { it.localDateTime.dayOfWeek }
        .mapValues { it.value.feelingCategoryPercents }

    return Breakdown(
        datePeriod = datePeriod,
        overallMood = feelingCategoryPercents,
        topFeelings = topFeelings,
        allFeelings = feelingFrequencies,
        dailyMood = dailyMood,
        weeklyMood = weeklyMood,
    )
}

fun List<CheckIn>.toBreakdowns(): Breakdowns {
    val earliestCheckInDate = minOf { it.localDateTime.toLocalDate() }
    val latestCheckInDate = maxOf { it.localDateTime.toLocalDate() }
    val allTimeBreakdown = toBreakdown(earliestCheckInDate..<latestCheckInDate)

    val yearlyBreakdown = groupBy { it.localDateTime.year }
        .map { (year, checkIn) ->
            val firstDayOfYear = LocalDate.of(year, 1, 1)
            val firstDayOfYearAfter = LocalDate.of(year, 1, 1)
                .plusYears(1)
            val datePeriod =  firstDayOfYear ..< firstDayOfYearAfter
            checkIn.toBreakdown(datePeriod)
        }

    val monthlyBreakdown = groupBy { it.localDateTime.year to it.localDateTime.month }
        .map { (yearAndMonth, checkIn) ->
            val (year, month) = yearAndMonth
            val firstDayOfMonth = LocalDate.of(year, month, 1)
            val firstDayOfMonthAfter = LocalDate.of(year, month, 1)
                .plusMonths(1)
            val datePeriod = firstDayOfMonth..<firstDayOfMonthAfter
            checkIn.toBreakdown(datePeriod)
        }

    return Breakdowns(
        allTime = allTimeBreakdown,
        yearly = yearlyBreakdown,
        monthly = monthlyBreakdown,
    )
}