package com.gentlekboy.openweatherapp.di

import android.app.Application
import com.gentlekboy.openweatherapp.data.database.OpenWeatherDb
import com.gentlekboy.openweatherapp.data.database.dao.CityResponseDao
import com.gentlekboy.openweatherapp.data.database.dao.CoordinatesResponseDao
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
    fun provideCoordinatesDao(openWeatherDb: OpenWeatherDb): CityResponseDao =
        openWeatherDb.coordinatesDao()

    @Singleton
    @Provides
    fun provideWeatherDao(openWeatherDb: OpenWeatherDb): CoordinatesResponseDao = openWeatherDb.weatherDao()
}