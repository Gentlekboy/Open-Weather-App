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
    @Json(name = "base")
    var base: String? = null,
    @Json(name = "clouds")
    var clouds: Clouds? = null,
    @Json(name = "cod")
    var cod: Int? = null,
    @Json(name = "coord")
    var coord: Coord,
    @Json(name = "dt")
    var dt: Int,
    @Json(name = "main")
    var main: Main? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "sys")
    var sys: Sys,
    @Json(name = "timezone")
    var timezone: Int? = null,
    @Json(name = "visibility")
    var visibility: Int? = null,
    @Json(name = "weather")
    var weather: List<Weather>? = null,
    @Json(name = "wind")
    var wind: Wind? = null,
    var isFavourite: Boolean = false
) : Parcelable