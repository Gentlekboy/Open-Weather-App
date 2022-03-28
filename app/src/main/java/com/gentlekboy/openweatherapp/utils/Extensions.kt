package com.gentlekboy.openweatherapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun Int.convertTimeStampToDate(): String {
    val date = Date(this.toLong() * 1000)
    val simpleDateFormat = SimpleDateFormat("EEE, MMM d, yyyy", Locale.ENGLISH)

    return simpleDateFormat.format(date)
}

fun Int.convertTimeStampToTime(): String {
    val date = Date(this.toLong() * 1000)
    val simpleDateFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)

    return simpleDateFormat.format(date)
}