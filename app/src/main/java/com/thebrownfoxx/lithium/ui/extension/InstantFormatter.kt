package com.thebrownfoxx.lithium.ui.extension

import java.time.ZoneId
import java.time.format.DateTimeFormatter

val TimeFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("h:mm a").withZone(ZoneId.systemDefault())

val DateFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("LLLL d, yyyy").withZone(ZoneId.systemDefault())

val TimedDateFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("LLLL d, yyyy, h:mm a").withZone(ZoneId.systemDefault())