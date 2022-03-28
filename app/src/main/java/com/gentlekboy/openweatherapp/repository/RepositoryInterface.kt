package com.gentlekboy.openweatherapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.CoordinatesResponse

interface RepositoryInterface {
    suspend fun saveAllResponsesToDb(apiKey: String, context: Context)

    suspend fun fetchCityResponseFromApiToDb(apiKey: String)

    suspend fun fetchCoordinatesResponseFromApiToDb(apiKey: String, context: Context)

    suspend fun deleteAllCoordinatesResponse()

    fun getCityResponseLiveData(): LiveData<List<CityResponse>>

    suspend fun updateCityResponse(cityResponse: CityResponse)

    fun getDailyWeatherLiveData(latitude: Double, longitude: Double): LiveData<CoordinatesResponse>
}