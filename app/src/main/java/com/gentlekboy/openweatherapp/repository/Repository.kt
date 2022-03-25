package com.gentlekboy.openweatherapp.repository

import com.gentlekboy.openweatherapp.data.model.city.CityWeatherResponse
import com.gentlekboy.openweatherapp.data.network.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiInterface: ApiInterface
) : RepositoryInterface {

    override suspend fun getWeatherByCity(
        cityName: String,
        apiKey: String
    ): Response<CityWeatherResponse> =
        apiInterface.getWeatherReportByCityName(cityName, apiKey)

    override suspend fun getWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ) = apiInterface.getWeatherReportByCoordinates(
        latitude = latitude,
        longitude = longitude,
        apiKey = apiKey
    )
}