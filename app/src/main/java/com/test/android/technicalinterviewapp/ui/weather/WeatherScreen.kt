package com.test.android.technicalinterviewapp.ui.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ExitToApp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.test.android.technicalinterviewapp.data.remote.model.WeatherData
import com.test.android.technicalinterviewapp.navigation.Routes
import com.test.android.technicalinterviewapp.ui.TextComponent
import com.test.android.technicalinterviewapp.ui.TopAppBarApplication
import com.test.android.technicalinterviewapp.util.Resource

@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val weatherState by weatherViewModel.weatherState.collectAsState()
    LaunchedEffect(Unit) {
        weatherViewModel.getCurrentWeather()
    }

    Column(modifier = modifier.fillMaxSize()) {
        // TopAppBar at the top
        TopAppBarApplication(
            title = "Today Weather",
            onActionClick = {
                navController.navigate(Routes.LOGIN_SCREEN)
            },
            showBackButton = true,
            onBackClick = {
                navController.popBackStack()
            },
            imageVectorForAction = Icons.Sharp.ExitToApp
        )

        // Main content centered in the remaining space
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .weight(1f), // Ensures the Box takes up the remaining space below the TopAppBar
            contentAlignment = Alignment.Center
        ) {
            when (weatherState) {
                is Resource.Loading -> {
                    CircularProgressIndicator(color = Color.Blue)
                }

                is Resource.Success -> {
                    val weatherData = (weatherState as Resource.Success<WeatherData>).data
                    WeatherData(weatherData = weatherData)
                }

                is Resource.Error -> {
                    val errorMessage = (weatherState as Resource.Error).error
                    TextComponent(
                        value = errorMessage,
                        textSize = 24.sp,
                        textColor = Color.Red
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewWeatherScreen() {
    WeatherScreen(modifier = Modifier, navController = rememberNavController())
}

@Composable
fun WeatherData(weatherData: WeatherData) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        TextComponent(
            value = "Temperature: ${weatherData.temperature} K",
            textSize = 24.sp,
            textColor = Color.Blue
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextComponent(
            value = "Weather Type: ${weatherData.weatherType}",
            textSize = 24.sp,
            textColor = Color.Blue
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextComponent(
            value = "Humidity: ${weatherData.humidity} %",
            textSize = 24.sp,
            textColor = Color.Blue
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextComponent(
            value = "Wind Speed: ${weatherData.windSpeed} Km/h",
            textSize = 24.sp,
            textColor = Color.Blue
        )
    }
}

