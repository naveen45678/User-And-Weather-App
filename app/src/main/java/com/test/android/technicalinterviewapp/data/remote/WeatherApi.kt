package com.test.android.technicalinterviewapp.data.remote

import com.test.android.technicalinterviewapp.data.remote.dto.WeatherResponse
import com.test.android.technicalinterviewapp.data.remote.model.WeatherData
import com.test.android.technicalinterviewapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("onecall")
     suspend fun getCurrentWeather(

        @Query("lat") lat: Double = 12.9082847623315,
        @Query("lon") lon: Double = 77.65197822993314,
        @Query("units") units: String = "imperial",
        @Query("appid") apiKey: String = Constants.API_ID
     ): WeatherResponse

}