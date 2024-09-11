package com.test.android.technicalinterviewapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.android.technicalinterviewapp.data.remote.model.WeatherData
import com.test.android.technicalinterviewapp.data.repository.WeatherRepository
import com.test.android.technicalinterviewapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    private val _weatherState = MutableStateFlow<Resource<WeatherData>>(Resource.Loading)
    val weatherState: StateFlow<Resource<WeatherData>> get() = _weatherState

    suspend fun getCurrentWeather() {
        viewModelScope.launch {
            try {
                val weatherData = weatherRepository.getCurrentWeather()
                _weatherState.value = Resource.Success(weatherData)

            } catch (e: Exception) {
                println("ther error is $e")
                _weatherState.value = Resource.Error(e.message ?: "An error occurred")

            }


        }

    }
}

