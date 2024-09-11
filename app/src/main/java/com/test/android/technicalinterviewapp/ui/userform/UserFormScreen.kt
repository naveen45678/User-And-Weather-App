package com.test.android.technicalinterviewapp.ui.userform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.test.android.technicalinterviewapp.navigation.Routes
import com.test.android.technicalinterviewapp.ui.ButtonComponent
import com.test.android.technicalinterviewapp.ui.OutlinedEditTextField
import com.test.android.technicalinterviewapp.ui.TextComponent
import com.test.android.technicalinterviewapp.util.showToast

@Composable
fun UserFormScreen(
    userFormViewModel: UserFormViewModel = hiltViewModel(),
    modifier: Modifier,
    navController: NavController
) {
    val isValidationSuccessful by userFormViewModel.isValidationSuccessful.observeAsState()
    var firstNameTextState by remember { mutableStateOf(TextFieldValue("")) }
    var lastNameTextState by remember { mutableStateOf(TextFieldValue("")) }
    var emailTextState by remember { mutableStateOf(TextFieldValue("")) }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current




    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextComponent(
            value = "Add User",
            textSize = 24.sp,
            textColor = Color.Blue,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedEditTextField(
            value = firstNameTextState,
            onValueChange = { newText ->
                firstNameTextState = newText
                userFormViewModel.firstName = newText.text


            },
            labelText = "firstName",
            errorMessage = userFormViewModel.firstNameError.value
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedEditTextField(
            value = lastNameTextState,
            onValueChange = { newText ->
                lastNameTextState = newText
                userFormViewModel.lastName = newText.text


            },
            labelText = "lastName",
            errorMessage = userFormViewModel.lastNameError.value
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedEditTextField(
            value = emailTextState,
            onValueChange = { newText ->
                emailTextState = newText
                userFormViewModel.emailId = newText.text


            },
            labelText = "email",
            errorMessage = userFormViewModel.emailIdError.value
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween // Ensures buttons are spaced out
        ) {
            ButtonComponent(
                value = "Submit",
                buttonColor = Color.Blue,
                textColor = Color.White,
                onClick = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    userFormViewModel.validateCredentials()
                    if (isValidationSuccessful==true){
                        context.showToast("User added successfully")
                        navController.navigate(Routes.USER_LIST_SCREEN)

                    }

                },
                textSize = 20.sp,
                modifier = Modifier.weight(1f) // Ensures the button takes up available width
            )

            Spacer(modifier = Modifier.width(16.dp)) // Add spacing between buttons

            ButtonComponent(
                value = "Cancel",
                buttonColor = Color.White,
                textColor = Color.Black,
                onClick = {
                    navController.navigate(Routes.USER_LIST_SCREEN)
                },
                textSize = 20.sp,
                modifier = Modifier.weight(1f) // Ensures the button takes up available width
            )

        }

    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewUserFormScreen() {
    UserFormScreen(modifier = Modifier, navController = rememberNavController())

}