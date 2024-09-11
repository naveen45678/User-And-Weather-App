package com.test.android.technicalinterviewapp.ui.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.test.android.technicalinterviewapp.navigation.AppNavHost
import com.test.android.technicalinterviewapp.navigation.Routes
import com.test.android.technicalinterviewapp.ui.theme.TechnicalInterviewAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TechnicalInterviewAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CheckUserLoginAndGoToDestinationScreen(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}
@Composable
fun CheckUserLoginAndGoToDestinationScreen(mainAcyivityViewModel: MainAcyivityViewModel =hiltViewModel(), modifier: Modifier){

    val isUserLoggedIn = mainAcyivityViewModel.isLoggedIn
    AppNavHost(
        modifier = modifier,
        startDestination = if (isUserLoggedIn) Routes.USER_LIST_SCREEN else Routes.ON_BOARDING_SCREEN
    )
}