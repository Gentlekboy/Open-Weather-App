package com.gentlekboy.openweatherapp.data.model.coordinatesresponse

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "coordinates_response_table")
@JsonClass(generateAdapter = true)
data class CoordinatesResponse(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @Json(name = "alerts")
    var alerts: List<Alert>? = null,
    @Json(name = "current")
    var current: Current? = null,
    @Json(name = "hourly")
    var hourly: List<Hourly>? = null,
    @Json(name = "lat")
    var lat: Double? = null,
    @Json(name = "lon")
    var lon: Double? = null,
    @Json(name = "timezone")
    var timezone: String? = null,
)