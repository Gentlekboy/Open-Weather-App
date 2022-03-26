package com.gentlekboy.openweatherapp.repository

import android.util.Log
import com.gentlekboy.openweatherapp.data.database.dao.CoordinatesDao
import com.gentlekboy.openweatherapp.data.database.dao.WeatherDao
import com.gentlekboy.openweatherapp.data.network.ApiInterface
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val coordinatesDao: CoordinatesDao,
    private val weatherDao: WeatherDao
) : RepositoryInterface {

    override suspend fun fetchCoordinatesFromApiToDb(
        cityName: String,
        apiKey: String
    ) {
        try {
            val response = apiInterface.fetchCoordinatesByCityName(cityName, apiKey)

            if (response.isSuccessful) {
                val data = response.body()

                if (data != null) coordinatesDao.insertCity(response.body()!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("GKB", "fetchCoordinatesFromApiToDb catch block in repository: ${e.message}")
        }
    }

    override suspend fun fetchWeatherFromApiToDb(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ) {
        try {
            val response = apiInterface.fetchWeatherReportByCoordinates(
                latitude = latitude,
                longitude = longitude,
                apiKey = apiKey
            )

            if (response.isSuccessful) {
                val data = response.body()

                if (data != null) weatherDao.insertWeatherData(response.body()!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("GKB", "fetchWeatherFromApiToDb catch block in repository: ${e.message}")
        }
    }

    override suspend fun getWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ) = apiInterface.fetchWeatherReportByCoordinates(
        latitude = latitude,
        longitude = longitude,
        apiKey = apiKey
    )

    override fun getCoordinatesFromDb() = coordinatesDao.fetchCityInfo()

    override fun getWeatherFromDb() = weatherDao.fetchWeatherData()
}