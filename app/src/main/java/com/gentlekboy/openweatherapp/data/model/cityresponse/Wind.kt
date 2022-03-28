package com.gentlekboy.openweatherapp.data.model.cityresponse

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "deg")
    var deg: Int? = null,
    @Json(name = "gust")
    var gust: Double? = null,
    @Json(name = "speed")
    var speed: Double? = null
) : Parcelable