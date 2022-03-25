package com.gentlekboy.openweatherapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gentlekboy.openweatherapp.R

class MainActivity : AppCompatActivity() {

    init {
        System.loadLibrary("api-keys")
    }

    private external fun getKeys() : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("GKB", "HIDDEN API-KEY -> ${getKeys()}")
    }
}