package com.gentlekboy.openweatherapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertTimeStampToLocalTime(timeStamp: Long): String {
    val date = Date(timeStamp * 1000)
    val simpleDateFormat = SimpleDateFormat("EEEEE, MMMMM d, yyyy", Locale.ENGLISH)

    return simpleDateFormat.format(date)
}