package com.gentlekboy.openweatherapp.repository

import com.gentlekboy.openweatherapp.data.model.city.CityWeatherResponse
import com.gentlekboy.openweatherapp.data.model.coordinates.CoordinateResponse
import retrofit2.Response

interface RepositoryInterface {
    suspend fun getWeatherByCity(
        cityName: String,
        apiKey: String
    ): Response<CityWeatherResponse>

    suspend fun getWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): Response<CoordinateResponse>
}