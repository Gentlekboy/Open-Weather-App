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

    override suspend fun saveDataToDb(apiKey: String) {
        fetchCoordinatesFromApiToDb(apiKey)
        fetchWeatherFromApiToDb(apiKey)
    }

    override suspend fun fetchCoordinatesFromApiToDb(apiKey: String) {
        try {
            val listOfTopCities = arrayListOf(
                "London",
                "Paris",
                "New York",
                "Moscow",
                "Dubai",
                "Barcelona",
                "Madrid",
                "Rome",
                "Doha",
                "Amsterdam",
                "Chicago",
                "Toronto",
                "Berlin",
                "Sydney",
                "Istanbul",
                "Beijing",
                "Milan",
                "Hong Kong",
                "Dublin",
                "Miami"
            )

            listOfTopCities.forEach { city ->
                val response = apiInterface.fetchCoordinatesByCityName(city, apiKey)

                if (response.isSuccessful) {
                    val data = response.body()

                    if (data != null) coordinatesDao.insertCity(data)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("GKB", "fetchCoordinatesFromApiToDb catch block in repository: ${e.message}")
        }
    }

    override suspend fun fetchWeatherFromApiToDb(apiKey: String) {
        try {
            val listOfCoordinates = coordinatesDao.fetchAllCoordinates()
            val listOfWeatherInfo = weatherDao.fetchListOfWeatherData()

            if (listOfWeatherInfo.isNotEmpty()) weatherDao.deleteAllWeatherData()

            listOfCoordinates.forEach { coordinate ->
                val response = apiInterface.fetchWeatherReportByCoordinates(
                    latitude = coordinate.lat,
                    longitude = coordinate.lon,
                    apiKey = apiKey
                )

                if (response.isSuccessful) {
                    val data = response.body()

                    if (data != null) weatherDao.insertWeatherData(data)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("GKB", "fetchWeatherFromApiToDb catch block in repository: ${e.message}")
        }
    }

    override suspend fun deleteAllWeatherData() = weatherDao.deleteAllWeatherData()

    override fun getWeatherLiveDataFromDb() = weatherDao.fetchWeatherLiveData()
}