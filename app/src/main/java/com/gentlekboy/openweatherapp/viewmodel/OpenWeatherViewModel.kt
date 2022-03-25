package com.gentlekboy.openweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gentlekboy.openweatherapp.data.model.city.CityWeatherResponse
import com.gentlekboy.openweatherapp.data.model.coordinates.CoordinateResponse
import com.gentlekboy.openweatherapp.data.model.responsestatus.Resource
import com.gentlekboy.openweatherapp.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpenWeatherViewModel @Inject constructor(
    private val repositoryInterface: RepositoryInterface
) : ViewModel() {

    private val _cityWeather: MutableLiveData<Resource<CityWeatherResponse>> = MutableLiveData()
    val cityWeather: LiveData<Resource<CityWeatherResponse>> = _cityWeather

    private val _coordinates: MutableLiveData<Resource<CoordinateResponse>> = MutableLiveData()
    val coordinates: LiveData<Resource<CoordinateResponse>> = _coordinates

    fun getWeatherByCityName(cityName: String, apiKey: String) {
        _cityWeather.postValue(Resource.loading())

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repositoryInterface.getWeatherByCity(cityName, apiKey)

                when (result.isSuccessful) {
                    true -> _cityWeather.postValue(
                        Resource.success(
                            result.body(),
                            "Successful"
                        )
                    )
                    false -> _cityWeather.postValue(Resource.error(result.message()))
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getWeatherByCoordinates(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _coordinates.postValue(Resource.loading())

            try {
                val result = repositoryInterface.getWeatherByCoordinates(
                    latitude,
                    longitude,
                    apiKey
                )

                when (result.isSuccessful) {
                    true -> _coordinates.postValue(
                        Resource.success(
                            result.body(),
                            "Successful"
                        )
                    )
                    false -> _coordinates.postValue(Resource.error(result.message()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}