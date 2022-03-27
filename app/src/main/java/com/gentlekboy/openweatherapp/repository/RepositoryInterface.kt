package com.gentlekboy.openweatherapp.repository

import androidx.lifecycle.LiveData
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.CoordinateResponse

interface RepositoryInterface {
    suspend fun saveDataToDb(apiKey: String)

    suspend fun fetchCoordinatesFromApiToDb(apiKey: String)

    suspend fun fetchWeatherFromApiToDb(apiKey: String)

    suspend fun deleteAllWeatherData()

    fun getWeatherLiveDataFromDb(): LiveData<List<CoordinateResponse>>
}