package com.gentlekboy.openweatherapp.data.model.coordinates


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoordinateResponse(
    @Json(name = "alerts")
    var alerts: List<Alert>,
    @Json(name = "current")
    var current: Current,
    @Json(name = "hourly")
    var hourly: List<Hourly>,
    @Json(name = "lat")
    var lat: Double,
    @Json(name = "lon")
    var lon: Double,
    @Json(name = "timezone")
    var timezone: String
)