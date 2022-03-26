package com.gentlekboy.openweatherapp.utils.typeconverter

import androidx.room.TypeConverter
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CoordinatesResponseTypeConverter {
    @TypeConverter
    fun fromAlertToString(alert: List<Alert>): String = Gson().toJson(alert)

    @TypeConverter
    fun fromStringToAlert(alert: String): List<Alert> {
        val listOfAlert = object : TypeToken<List<Alert>>() {}.type
        return Gson().fromJson(alert, listOfAlert)
    }

    @TypeConverter
    fun fromWeatherToString(weather: Weather): String = Gson().toJson(weather)

    @TypeConverter
    fun fromStringToWeather(weather: String): Weather =
        Gson().fromJson(weather, Weather::class.java)

    @TypeConverter
    fun fromCurrentToString(current: Current): String = Gson().toJson(current)

    @TypeConverter
    fun fromStringToCurrent(current: String): Current =
        Gson().fromJson(current, Current::class.java)

    @TypeConverter
    fun fromWeatherXToString(weatherX: WeatherX): String = Gson().toJson(weatherX)

    @TypeConverter
    fun fromStringToWeatherX(weatherX: String): WeatherX =
        Gson().fromJson(weatherX, WeatherX::class.java)

    @TypeConverter
    fun fromHourlyToString(hourly: List<Hourly>): String = Gson().toJson(hourly)

    @TypeConverter
    fun fromStringToHourly(hourly: String): List<Hourly> {
        val listOfHourly = object : TypeToken<List<Hourly>>() {}.type
        return Gson().fromJson(hourly, listOfHourly)
    }
}