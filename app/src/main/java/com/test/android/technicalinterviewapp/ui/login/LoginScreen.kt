package com.test.android.technicalinterviewapp.ui.login

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.test.android.technicalinterviewapp.navigation.Routes
import com.test.android.technicalinterviewapp.ui.ButtonComponent
import com.test.android.technicalinterviewapp.ui.OutlinedEditTextField
import com.test.android.technicalinterviewapp.ui.TextComponent
import com.test.android.technicalinterviewapp.util.showToast


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    modifier: Modifier,
    navController: NavController
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    BackHandler(enabled = true) {
        (context as? Activity)?.finish()
    }
    val isUserLoggedIn by loginViewModel.isLoggedIn.observeAsState(false)
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(isUserLoggedIn) {
        if (isUserLoggedIn) {
            context.showToast(message = "Successfully logged in")
            navController.navigate(Routes.USER_LIST_SCREEN) {
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus() // Clear focus when tapping outside
                    keyboardController?.hide() // Hide the keyboard
                })
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var userNameTextState by remember { mutableStateOf(TextFieldValue("")) }
            var passwordTextState by remember { mutableStateOf(TextFieldValue("")) }

            Spacer(modifier = Modifier.height(16.dp))

            TextComponent(
                value = "Login",
                textSize = 30.sp,
                textColor = Color.Blue,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedEditTextField(
                value = userNameTextState,
                onValueChange = { newText ->
                    userNameTextState = newText
                    loginViewModel.username = newText.text  // Updating ViewModel's username
                },
                labelText = "Username",
                modifier = Modifier.fillMaxWidth(),
                errorMessage = loginViewModel.usernameError.value
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedEditTextField(
                value = passwordTextState,
                onValueChange = { newText ->
                    passwordTextState = newText
                    loginViewModel.password = newText.text  // Updating ViewModel's password
                },
                labelText = "Password",
                modifier = Modifier.fillMaxWidth(),
                errorMessage = loginViewModel.passwordError.value
            )
            Spacer(modifier = Modifier.height(16.dp))

            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                value = "Login",
                buttonColor = Color.Blue,
                textColor = Color.White,
                onClick = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    loginViewModel.validateCredentials()
                },
                textSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen(){
    LoginScreen(modifier = Modifier, navController = rememberNavController())
}