package com.gentlekboy.openweatherapp.utils.typeconverter

import androidx.room.TypeConverter
import com.gentlekboy.openweatherapp.data.model.cityresponse.Coord
import com.gentlekboy.openweatherapp.data.model.cityresponse.Sys
import com.google.gson.Gson

class CityResponseTypeConverter {

    @TypeConverter
    fun fromCoordinateToString(coord: Coord): String = Gson().toJson(coord)

    @TypeConverter
    fun fromStringToCoordinate(coord: String): Coord = Gson().fromJson(coord, Coord::class.java)

    @TypeConverter
    fun fromSysToString(sys: Sys): String = Gson().toJson(sys)

    @TypeConverter
    fun fromStringToSys(sys: String): Sys = Gson().fromJson(sys, Sys::class.java)
}