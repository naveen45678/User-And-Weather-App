package com.test.android.technicalinterviewapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.android.technicalinterviewapp.data.local.PreferencesManager
import com.test.android.technicalinterviewapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    var username by mutableStateOf("")
    var password by mutableStateOf("")

    // Error messages for username and password validation
    var usernameError = mutableStateOf<String?>(null)
    var passwordError = mutableStateOf<String?>(null)

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn




    private fun loginUser() {
        viewModelScope.launch {
            preferencesManager.setLoggedIn(true)
            _isLoggedIn.value = true
        }
    }

    fun validateCredentials() {
        // Reset errors
        usernameError.value = null
        passwordError.value = null

        // Validate username
        if (username.isBlank()) {
            usernameError.value = "Username cannot be empty"
        } else if (username != Constants.USER_NAME) {
            usernameError.value = "Incorrect username"
        }

        // Validate password
        if (password.isBlank()) {
            passwordError.value = "Password cannot be empty"
        } else if (password != Constants.USER_PASSWORD) {
            passwordError.value = "Incorrect password"
        }

        // If no errors, log the user in
        if (usernameError.value == null && passwordError.value == null) {
            loginUser()
        }
    }
}
