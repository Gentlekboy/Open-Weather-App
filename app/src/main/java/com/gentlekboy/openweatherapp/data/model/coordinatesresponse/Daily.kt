package com.gentlekboy.openweatherapp.data.model.coordinatesresponse

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Daily(
    @Json(name = "dt")
    var dt: Int,
    var temp: Temp,
    @Json(name = "weather")
    var weather: List<WeatherX>
)