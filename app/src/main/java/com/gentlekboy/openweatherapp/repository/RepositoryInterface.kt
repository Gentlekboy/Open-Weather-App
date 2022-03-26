package com.gentlekboy.openweatherapp.repository

import androidx.lifecycle.LiveData
import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.CoordinateResponse
import retrofit2.Response

interface RepositoryInterface {
    suspend fun fetchCoordinatesFromApiToDb(
        cityName: String,
        apiKey: String
    )

    suspend fun fetchWeatherFromApiToDb(
        latitude: Double,
        longitude: Double,
        apiKey: String
    )

    suspend fun getWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): Response<CoordinateResponse>

    fun getCoordinatesFromDb(): LiveData<CityResponse>

    fun getWeatherFromDb(): LiveData<CoordinateResponse>
}