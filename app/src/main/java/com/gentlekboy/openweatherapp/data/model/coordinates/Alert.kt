package com.gentlekboy.openweatherapp.data.model.coordinates


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Alert(
    @Json(name = "description")
    var description: String,
    @Json(name = "end")
    var end: Int,
    @Json(name = "event")
    var event: String,
    @Json(name = "sender_name")
    var senderName: String,
    @Json(name = "start")
    var start: Int,
    @Json(name = "tags")
    var tags: List<String>
)