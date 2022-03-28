package com.gentlekboy.openweatherapp.ui.activity

import android.os.Bundle
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

        openWeatherViewModel.saveDataToDb(getKeys(), this)
    }
}