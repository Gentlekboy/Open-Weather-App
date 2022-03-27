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

    fun saveDataToDb(apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.saveDataToDb(apiKey)
        }
    }

    fun getWeatherFromDb() = repositoryInterface.getWeatherLiveDataFromDb()
}