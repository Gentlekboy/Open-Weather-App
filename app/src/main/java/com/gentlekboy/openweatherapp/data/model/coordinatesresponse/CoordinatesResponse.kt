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
    @Json(name = "daily")
    var daily: List<Daily>,
    @Json(name = "lat")
    var lat: Double,
    @Json(name = "lon")
    var lon: Double
)