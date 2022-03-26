package com.gentlekboy.openweatherapp.di

import android.app.Application
import com.gentlekboy.openweatherapp.data.database.OpenWeatherDb
import com.gentlekboy.openweatherapp.data.database.dao.CoordinatesDao
import com.gentlekboy.openweatherapp.data.database.dao.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Application): OpenWeatherDb = OpenWeatherDb.getInstance(context)

    @Singleton
    @Provides
    fun provideCoordinatesDao(openWeatherDb: OpenWeatherDb): CoordinatesDao =
        openWeatherDb.coordinatesDao()

    @Singleton
    @Provides
    fun provideWeatherDao(openWeatherDb: OpenWeatherDb): WeatherDao = openWeatherDb.weatherDao()
}