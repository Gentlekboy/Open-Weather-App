package com.gentlekboy.openweatherapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gentlekboy.openweatherapp.R
import com.gentlekboy.openweatherapp.viewmodel.OpenWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    init {
        System.loadLibrary("api-keys")
    }

    private val openWeatherViewModel: OpenWeatherViewModel by viewModels()
    private external fun getKeys(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openWeatherViewModel.fetchCoordinatesFromApiToDb("Lagos", getKeys())
        openWeatherViewModel.fetchWeatherFromApiToDb(
            41.3888,
            2.159,
            getKeys()
        )

        openWeatherViewModel.getCoordinatesFromDb().observe(this) {
            Log.d("GKB", "onCreate: $it")
        }

        openWeatherViewModel.getWeatherFromDb().observe(this) {
            Log.d("GKB", "onCreate: $it")
        }
    }
}