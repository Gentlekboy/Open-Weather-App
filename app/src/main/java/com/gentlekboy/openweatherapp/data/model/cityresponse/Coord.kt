package com.gentlekboy.openweatherapp.data.model.cityresponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coord(
    @Json(name = "lat")
    var lat: Double,
    @Json(name = "lon")
    var lon: Double
)