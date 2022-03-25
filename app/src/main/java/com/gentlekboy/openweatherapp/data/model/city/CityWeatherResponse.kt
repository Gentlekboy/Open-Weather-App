package com.gentlekboy.openweatherapp.data.model.city

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityWeatherResponse(
    @Json(name = "coord")
    var coord: Coord,
    @Json(name = "id")
    var id: Int,
    @Json(name = "name")
    var name: String,
    @Json(name = "sys")
    var sys: Sys,
    @Json(name = "timezone")
    var timezone: Int,
)