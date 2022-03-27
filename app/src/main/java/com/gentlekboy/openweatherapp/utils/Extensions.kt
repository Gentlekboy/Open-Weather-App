package com.gentlekboy.openweatherapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun Int.convertTimeStampToLocalTime(): String {
    val date = Date(this.toLong() * 1000)
    val simpleDateFormat = SimpleDateFormat("EEEE, MMM d, yyyy", Locale.ENGLISH)

    return simpleDateFormat.format(date)
}