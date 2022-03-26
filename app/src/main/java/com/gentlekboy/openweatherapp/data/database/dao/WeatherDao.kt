package com.gentlekboy.openweatherapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.CoordinateResponse

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(coordinateResponse: CoordinateResponse)

    @Query("SELECT * FROM weather_table")
    fun fetchWeatherData(): LiveData<CoordinateResponse>

    @Update
    suspend fun updateWeatherData(coordinateResponse: CoordinateResponse)
}