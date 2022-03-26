package com.gentlekboy.openweatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gentlekboy.openweatherapp.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpenWeatherViewModel @Inject constructor(
    private val repositoryInterface: RepositoryInterface
) : ViewModel() {

    fun fetchCoordinatesFromApiToDb(cityName: String, apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.fetchCoordinatesFromApiToDb(cityName, apiKey)
        }
    }

    fun fetchWeatherFromApiToDb(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.fetchWeatherFromApiToDb(latitude, longitude, apiKey)
        }
    }

    fun getCoordinatesFromDb() = repositoryInterface.getCoordinatesFromDb()

    fun getWeatherFromDb() = repositoryInterface.getWeatherFromDb()
}