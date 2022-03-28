package com.gentlekboy.openweatherapp.data.model.cityresponse


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "description")
    var description: String,
    @Json(name = "icon")
    var icon: String? = null,
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "main")
    var main: String? = null
) : Parcelable