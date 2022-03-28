package com.gentlekboy.openweatherapp.data.model.cityresponse

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "city_response_table")
@JsonClass(generateAdapter = true)
data class CityResponse(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "coord")
    var coord: Coord,
    @Json(name = "dt")
    var dt: Int,
    @Json(name = "main")
    var main: Main,
    @Json(name = "name")
    var name: String,
    @Json(name = "sys")
    var sys: Sys,
    @Json(name = "weather")
    var weather: List<Weather>,
    var isFavourite: Boolean = false
) : Parcelable