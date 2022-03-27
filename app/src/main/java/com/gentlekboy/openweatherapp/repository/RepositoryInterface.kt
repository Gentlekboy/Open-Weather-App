package com.gentlekboy.openweatherapp.repository

import androidx.lifecycle.LiveData
import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.CoordinatesResponse

interface RepositoryInterface {
    suspend fun saveAllResponsesToDb(apiKey: String)

    suspend fun fetchCityResponseFromApiToDb(apiKey: String)

    suspend fun fetchCoordinatesResponseFromApiToDb(apiKey: String)

    suspend fun deleteAllCoordinatesResponse()

    fun getCoordinatesResponseLiveData(): LiveData<List<CoordinatesResponse>>

    fun getCityResponseLiveData(): LiveData<List<CityResponse>>

    suspend fun updateCityResponse(cityResponse: CityResponse)
}