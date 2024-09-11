package com.test.android.technicalinterviewapp.navigation

import OnBoardingScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.android.technicalinterviewapp.ui.login.LoginScreen
import com.test.android.technicalinterviewapp.ui.userform.UserFormScreen
import com.test.android.technicalinterviewapp.ui.userlist.UserListScreen
import com.test.android.technicalinterviewapp.ui.weather.WeatherScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.ON_BOARDING_SCREEN
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Routes.ON_BOARDING_SCREEN) { OnBoardingScreen(modifier = modifier,navController = navController) }
        composable(route = Routes.LOGIN_SCREEN) { LoginScreen(modifier = modifier, navController = navController) }
        composable(route = Routes.USER_LIST_SCREEN) { UserListScreen(modifier = modifier,navController= navController) }
        composable(route = Routes.USER_FORM_SCREEN) { UserFormScreen(modifier = modifier, navController = navController) }
        composable(route = Routes.WEATHER_SCREEN) { WeatherScreen(modifier = modifier, navController = navController) }
    }
}
