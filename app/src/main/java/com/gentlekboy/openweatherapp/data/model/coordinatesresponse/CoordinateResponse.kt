package com.gentlekboy.openweatherapp.data.model.coordinatesresponse

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "weather_table")
@JsonClass(generateAdapter = true)
data class CoordinateResponse(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @Json(name = "alerts")
    var alerts: List<Alert>? = null,
    @Json(name = "current")
    var current: Current,
    @Json(name = "hourly")
    var hourly: List<Hourly>,
    @Json(name = "lat")
    var lat: Double,
    @Json(name = "lon")
    var lon: Double,
    @Json(name = "timezone")
    var timezone: String,
    var isFavourite: Boolean = false
)