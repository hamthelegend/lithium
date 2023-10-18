package com.thebrownfoxx.lithium.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

typealias FeelingCategoryPercents = Map<FeelingCategory, Int>

typealias FeelingFrequencies = Map<Feeling, Int>

enum class DayPeriod(val range: OpenEndRange<LocalTime>) {
    Morning(LocalTime.of(6, 0)..<LocalTime.of(12, 0)),
    Afternoon(LocalTime.of(12, 0)..<LocalTime.of(18, 0)),
    Evening(LocalTime.of(18, 0)..< LocalTime.of(22, 0)),
    Night(LocalTime.of(22, 0)..<LocalTime.of(6, 0)),
}

typealias DailyMood = Map<DayPeriod, FeelingCategoryPercents>

typealias WeeklyMood = Map<DayOfWeek, FeelingCategoryPercents>

data class Breakdown(
    val datePeriod: OpenEndRange<LocalDate>,
    val feelingCategoryPercents: FeelingCategoryPercents,
    val feelingFrequencies: FeelingFrequencies,
    val dailyMood: DailyMood,
    val weeklyMood: WeeklyMood,
)

data class Breakdowns(
    val allTime: Breakdown,
    val yearly: List<Breakdown>,
    val monthly: List<Breakdown>,
)