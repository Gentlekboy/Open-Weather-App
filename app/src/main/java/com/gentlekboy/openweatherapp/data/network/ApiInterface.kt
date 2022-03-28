package com.gentlekboy.openweatherapp.data.network

import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.CoordinatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("weather")
    suspend fun fetchCoordinatesByCityName(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<CityResponse>

    @GET("onecall")
    suspend fun fetchWeatherReportByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String = "minutely,hourly",
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<CoordinatesResponse>
}