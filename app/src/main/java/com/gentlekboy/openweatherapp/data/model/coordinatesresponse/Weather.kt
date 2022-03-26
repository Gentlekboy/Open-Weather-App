package com.gentlekboy.openweatherapp.data.model.coordinatesresponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "description")
    var description: String,
    @Json(name = "icon")
    var icon: String,
    @Json(name = "id")
    var id: Int,
    @Json(name = "main")
    var main: String
)