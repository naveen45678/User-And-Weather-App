package com.test.android.technicalinterviewapp.data.remote.model

import com.test.android.technicalinterviewapp.data.remote.dto.WeatherResponse

//
data class WeatherData(
    var temperature: Double,
    var weatherType: String,
    var humidity: Int,
    var windSpeed: Double
)

fun WeatherResponse.toWeatherData(): WeatherData {
    return WeatherData(
        temperature = current.temp,
        weatherType = current.weather[0].main,
        humidity = current.humidity,
        windSpeed = current.wind_speed
    )
}
