package com.gentlekboy.openweatherapp.data.model.coordinatesresponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Temp(
    @Json(name = "day")
    var day: Double
)