package com.gentlekboy.openweatherapp.repository

import android.content.Context
import android.util.Log
import com.gentlekboy.openweatherapp.data.database.dao.CityResponseDao
import com.gentlekboy.openweatherapp.data.database.dao.CoordinatesResponseDao
import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse
import com.gentlekboy.openweatherapp.data.network.ApiInterface
import com.gentlekboy.openweatherapp.utils.isInternetAvailable
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val cityResponseDao: CityResponseDao,
    private val coordinatesResponseDao: CoordinatesResponseDao
) : RepositoryInterface {

    override suspend fun saveAllResponsesToDb(apiKey: String, context: Context) {
        fetchCityResponseFromApiToDb(apiKey)
        fetchCoordinatesResponseFromApiToDb(apiKey, context)
    }

    override suspend fun fetchCityResponseFromApiToDb(apiKey: String) {
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

                    if (data != null) cityResponseDao.insertCityResponse(data)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("GKB", "fetchCoordinatesFromApiToDb catch block in repository: ${e.message}")
        }
    }

    override suspend fun fetchCoordinatesResponseFromApiToDb(apiKey: String, context: Context) {
        try {
            val listOfCoordinates = cityResponseDao.fetchAllCoordinates()
            val coordinatesResponseList = coordinatesResponseDao.fetchCoordinatesResponseList()

            if (coordinatesResponseList.isNotEmpty() && context.isInternetAvailable()) {
                coordinatesResponseDao.deleteAllCoordinatesResponse()
            }

            listOfCoordinates.forEach { coordinate ->
                val response = apiInterface.fetchWeatherReportByCoordinates(
                    latitude = coordinate.lat,
                    longitude = coordinate.lon,
                    apiKey = apiKey
                )

                if (response.isSuccessful) {
                    val data = response.body()

                    if (data != null) coordinatesResponseDao.insertCoordinatesResponse(data)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("GKB", "fetchWeatherFromApiToDb catch block in repository: ${e.message}")
        }
    }

    override suspend fun deleteAllCoordinatesResponse() =
        coordinatesResponseDao.deleteAllCoordinatesResponse()

    override fun getCityResponseLiveData() = cityResponseDao.getCityResponseLiveData()

    override suspend fun updateCityResponse(cityResponse: CityResponse) =
        cityResponseDao.updateCityResponse(cityResponse)

    override fun getDailyWeatherLiveData(latitude: Double, longitude: Double) =
        coordinatesResponseDao.getDailyWeatherLiveData(latitude, longitude)
}