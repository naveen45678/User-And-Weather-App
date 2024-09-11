package com.test.android.technicalinterviewapp.data.repository

import com.test.android.technicalinterviewapp.data.remote.WeatherApi
import com.test.android.technicalinterviewapp.data.remote.model.WeatherData
import com.test.android.technicalinterviewapp.data.remote.model.toWeatherData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi) {
    suspend fun getCurrentWeather(): WeatherData{
        return weatherApi.getCurrentWeather().toWeatherData()

    }
}