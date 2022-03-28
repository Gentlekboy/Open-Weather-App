package com.gentlekboy.openweatherapp.utils

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    fun convertTimeStampToDate(timeStamp: Int): String {
        val date = Date(timeStamp.toLong() * 1000)
        val simpleDateFormat = SimpleDateFormat("EEE, MMM d, yyyy", Locale.ENGLISH)

        return simpleDateFormat.format(date)
    }

    fun convertTimeStampToTime(timeStamp: Int): String {
        val date = Date(timeStamp.toLong() * 1000)
        val simpleDateFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)

        return simpleDateFormat.format(date)
    }

    fun addCelsiusSign(temperature: String) = "$temperature \u2103"

    fun capitalizeFirstLetterOfEachWord(phrase: String) = phrase.split(" ").joinToString(" ") {
        it.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
    }.trimEnd()
}