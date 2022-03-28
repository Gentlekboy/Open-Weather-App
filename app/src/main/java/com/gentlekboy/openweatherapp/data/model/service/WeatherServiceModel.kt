package com.gentlekboy.openweatherapp.data.model.service

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherServiceModel(
    val temperature: Double,
    val city: String,
    val weatherDescription: String
) : Parcelable