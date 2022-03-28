package com.gentlekboy.openweatherapp.data.model.cityresponse


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Coord(
    @Json(name = "lat")
    var lat: Double,
    @Json(name = "lon")
    var lon: Double
) : Parcelable