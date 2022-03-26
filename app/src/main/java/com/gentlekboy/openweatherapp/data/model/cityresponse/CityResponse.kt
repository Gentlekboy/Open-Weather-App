package com.gentlekboy.openweatherapp.data.model.cityresponse

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "coordinates_table")
@JsonClass(generateAdapter = true)
data class CityResponse(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    var id: Int,
    @Json(name = "coord")
    var coord: Coord,
    @Json(name = "name")
    var name: String,
    @Json(name = "sys")
    var sys: Sys,
    @Json(name = "timezone")
    var timezone: Int,
)