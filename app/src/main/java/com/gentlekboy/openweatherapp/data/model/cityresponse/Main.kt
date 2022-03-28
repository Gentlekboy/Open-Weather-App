package com.gentlekboy.openweatherapp.data.model.cityresponse


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "feels_like")
    var feelsLike: Double? = null,
    @Json(name = "humidity")
    var humidity: Int? = null,
    @Json(name = "pressure")
    var pressure: Int? = null,
    @Json(name = "temp")
    var temp: Double,
    @Json(name = "temp_max")
    var tempMax: Double? = null,
    @Json(name = "temp_min")
    var tempMin: Double? = null
) : Parcelable