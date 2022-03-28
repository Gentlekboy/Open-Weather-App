package com.gentlekboy.openweatherapp.data.model.cityresponse


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Sys(
    @Json(name = "country")
    var country: String,
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "sunrise")
    var sunrise: Int? = null,
    @Json(name = "sunset")
    var sunset: Int? = null,
    @Json(name = "type")
    var type: Int? = null
) : Parcelable