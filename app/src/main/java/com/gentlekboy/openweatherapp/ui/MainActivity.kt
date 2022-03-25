package com.gentlekboy.openweatherapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gentlekboy.openweatherapp.R
import com.gentlekboy.openweatherapp.data.model.responsestatus.Status
import com.gentlekboy.openweatherapp.viewmodel.OpenWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val openWeatherViewModel: OpenWeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openWeatherViewModel.getWeatherByCityName("Lagos", "API_KEY_HERE")
        openWeatherViewModel.getWeatherByCoordinates(
            41.3888,
            2.159,
            "API_KEY_HERE"
        )

        openWeatherViewModel.cityWeather.observe(this) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    Log.d("GKB", "observe weatherByCityLivedata: $response")
                    Log.d("GKB", "")
                }
                else -> {}
            }
        }

        openWeatherViewModel.coordinates.observe(this) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    Log.d("GKB", "observe weatherByCoordinateLivedata: $response")
                    Log.d("GKB", "")
                }
                else -> {}
            }
        }
    }
}