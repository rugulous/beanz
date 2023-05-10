package com.rugulous.beanz.extensions

import java.time.LocalDate
import java.time.ZoneOffset

fun LocalDate.toMillis(): Long{
    return this.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
}