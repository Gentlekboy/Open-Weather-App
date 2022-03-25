package com.gentlekboy.openweatherapp.data.model.coordinates


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hourly(
    @Json(name = "clouds")
    var clouds: Int,
    @Json(name = "dew_point")
    var dewPoint: Double,
    @Json(name = "dt")
    var dt: Int,
    @Json(name = "feels_like")
    var feelsLike: Double,
    @Json(name = "humidity")
    var humidity: Int,
    @Json(name = "pressure")
    var pressure: Int,
    @Json(name = "temp")
    var temp: Double,
    @Json(name = "uvi")
    var uvi: Double,
    @Json(name = "visibility")
    var visibility: Int,
    @Json(name = "weather")
    var weather: List<WeatherX>,
    @Json(name = "wind_deg")
    var windDeg: Int,
    @Json(name = "wind_gust")
    var windGust: Double,
    @Json(name = "wind_speed")
    var windSpeed: Double
)