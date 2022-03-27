package com.gentlekboy.openweatherapp.data.model.coordinatesresponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Alert(
    @Json(name = "description")
    var description: String? = null,
    @Json(name = "end")
    var end: Int? = null,
    @Json(name = "event")
    var event: String? = null,
    @Json(name = "sender_name")
    var senderName: String? = null,
    @Json(name = "start")
    var start: Int? = null,
    @Json(name = "tags")
    var tags: List<String>? = null
)