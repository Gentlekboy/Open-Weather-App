package com.gentlekboy.openweatherapp.utils

import junit.framework.TestCase
import org.junit.Test

class UtilsTest : TestCase() {

    @Test
    fun convert_time_stamp_to_date_return_string() {
        val result = Utils().convertTimeStampToDate(1648505910)
        assertEquals("Mon, Mar 28, 2022", result)
    }

    @Test
    fun convert_time_stamp_to_time_return_string() {
        val result = Utils().convertTimeStampToDate(1648505910)
        assertEquals("5:00 AM", result)
    }
}