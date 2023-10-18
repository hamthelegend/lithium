package com.thebrownfoxx.lithium.extension

import java.time.LocalTime

infix fun LocalTime.inBetween(timeRange: OpenEndRange<LocalTime>) = when {
    timeRange.start <= timeRange.endExclusive -> this in timeRange
    else -> this in timeRange.start..LocalTime.of(23, 59, 59, 999999999) ||
            this in LocalTime.of(0, 0)..<timeRange.endExclusive
}