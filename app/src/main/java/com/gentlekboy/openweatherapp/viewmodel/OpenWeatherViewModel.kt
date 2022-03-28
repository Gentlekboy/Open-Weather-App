package com.gentlekboy.openweatherapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse
import com.gentlekboy.openweatherapp.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpenWeatherViewModel @Inject constructor(
    private val repositoryInterface: RepositoryInterface
) : ViewModel() {

    fun saveDataToDb(apiKey: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.saveAllResponsesToDb(apiKey, context)
        }
    }

    fun getCityResponseLiveData() = repositoryInterface.getCityResponseLiveData()

    fun updateCityResponse(cityResponse: CityResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.updateCityResponse(cityResponse)
        }
    }

    fun getDailyWeatherLiveData(latitude: Double, longitude: Double) =
        repositoryInterface.getDailyWeatherLiveData(latitude, longitude)

    fun searchDatabaseForCity(cityName: String) =
        repositoryInterface.searchDatabaseForCity(cityName)
}