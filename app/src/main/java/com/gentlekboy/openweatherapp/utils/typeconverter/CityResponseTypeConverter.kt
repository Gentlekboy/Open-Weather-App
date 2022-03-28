package com.gentlekboy.openweatherapp.utils.typeconverter

import androidx.room.TypeConverter
import com.gentlekboy.openweatherapp.data.model.cityresponse.Coord
import com.gentlekboy.openweatherapp.data.model.cityresponse.Main
import com.gentlekboy.openweatherapp.data.model.cityresponse.Sys
import com.gentlekboy.openweatherapp.data.model.cityresponse.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CityResponseTypeConverter {

    @TypeConverter
    fun fromCoordinateToString(coord: Coord): String = Gson().toJson(coord)

    @TypeConverter
    fun fromStringToCoordinate(coord: String): Coord = Gson().fromJson(coord, Coord::class.java)

    @TypeConverter
    fun fromSysToString(sys: Sys): String = Gson().toJson(sys)

    @TypeConverter
    fun fromStringToSys(sys: String): Sys = Gson().fromJson(sys, Sys::class.java)

    @TypeConverter
    fun fromMainToString(main: Main): String = Gson().toJson(main)

    @TypeConverter
    fun fromStringToMain(main: String): Main = Gson().fromJson(main, Main::class.java)

    @TypeConverter
    fun fromWeatherToString(weather: List<Weather>?): String = Gson().toJson(weather)

    @TypeConverter
    fun fromStringToWeather(weather: String): List<Weather>? {
        val listOfWeather = object : TypeToken<List<Weather>?>() {}.type
        return Gson().fromJson(weather, listOfWeather)
    }
}