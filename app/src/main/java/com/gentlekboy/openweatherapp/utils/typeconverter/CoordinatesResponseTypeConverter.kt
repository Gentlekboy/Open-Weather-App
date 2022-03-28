package com.gentlekboy.openweatherapp.utils.typeconverter

import androidx.room.TypeConverter
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.Daily
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.Temp
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.WeatherX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CoordinatesResponseTypeConverter {

//    @TypeConverter
//    fun fromWeatherToString(weather: Weather): String = Gson().toJson(weather)
//
//    @TypeConverter
//    fun fromStringToWeather(weather: String): Weather =
//        Gson().fromJson(weather, Weather::class.java)

    @TypeConverter
    fun fromTempToString(temp: Temp): String = Gson().toJson(temp)

    @TypeConverter
    fun fromStringToTemp(temp: String): Temp =
        Gson().fromJson(temp, Temp::class.java)

    @TypeConverter
    fun fromDailyToString(daily: List<Daily>): String = Gson().toJson(daily)

    @TypeConverter
    fun fromStringToDaily(daily: String): List<Daily> {
        val listOfDaily = object : TypeToken<List<Daily>>() {}.type
        return Gson().fromJson(daily, listOfDaily)
    }

    @TypeConverter
    fun fromWeatherXToString(weatherX: List<WeatherX>): String = Gson().toJson(weatherX)

    @TypeConverter
    fun fromStringToWeatherX(weatherX: String): List<WeatherX> {
        val listOfWeatherX = object : TypeToken<List<WeatherX>>() {}.type
        return Gson().fromJson(weatherX, listOfWeatherX)
    }
}