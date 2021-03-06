package com.gentlekboy.openweatherapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gentlekboy.openweatherapp.data.database.dao.CityResponseDao
import com.gentlekboy.openweatherapp.data.database.dao.CoordinatesResponseDao
import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.CoordinatesResponse
import com.gentlekboy.openweatherapp.utils.typeconverter.CityResponseTypeConverter
import com.gentlekboy.openweatherapp.utils.typeconverter.CoordinatesResponseTypeConverter

@Database(
    entities = [CityResponse::class, CoordinatesResponse::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CityResponseTypeConverter::class, CoordinatesResponseTypeConverter::class)
abstract class OpenWeatherDb : RoomDatabase() {
    abstract fun coordinatesDao(): CityResponseDao
    abstract fun weatherDao(): CoordinatesResponseDao

    companion object {
        @Volatile
        private var dbInstance: OpenWeatherDb? = null
        private val lock = Any()

        operator fun invoke(context: Context) = dbInstance ?: synchronized(lock) {
            dbInstance ?: getInstance(context)
        }

        fun getInstance(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            OpenWeatherDb::class.java,
            "open_weather_db"
        ).build()
    }
}