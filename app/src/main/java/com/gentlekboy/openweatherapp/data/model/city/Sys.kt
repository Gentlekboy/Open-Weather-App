package com.gentlekboy.openweatherapp.data.model.city


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sys(
    @Json(name = "country")
    var country: String
)