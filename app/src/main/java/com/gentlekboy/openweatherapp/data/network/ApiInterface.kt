package com.gentlekboy.openweatherapp.data.network

import com.gentlekboy.openweatherapp.data.model.city.CityWeatherResponse
import com.gentlekboy.openweatherapp.data.model.coordinates.CoordinateResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("weather")
    suspend fun getWeatherReportByCityName(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): Response<CityWeatherResponse>

    @GET("onecall")
    suspend fun getWeatherReportByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String = "minutely,daily",
        @Query("appid") apiKey: String
    ): Response<CoordinateResponse>
}