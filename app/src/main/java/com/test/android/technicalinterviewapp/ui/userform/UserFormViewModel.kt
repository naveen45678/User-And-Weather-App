package com.test.android.technicalinterviewapp.ui.userform

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.android.technicalinterviewapp.data.local.entity.User
import com.test.android.technicalinterviewapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserFormViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var emailId by mutableStateOf("")

    var firstNameError = mutableStateOf<String?>(null)
    var lastNameError = mutableStateOf<String?>(null)
    var emailIdError = mutableStateOf<String?>(null)

    private var _isValidationSuccessful = MutableLiveData(false)
    val isValidationSuccessful get() = _isValidationSuccessful


    fun validateCredentials() {
        // Reset errors
        firstNameError.value = null
        lastNameError.value = null
        emailIdError.value = null

        // Validate username
        if (firstName.isBlank()) {
            firstNameError.value = "firstName cannot be empty"
        }

        // Validate password
        if (lastName.isBlank()) {
            lastNameError.value = "LastName cannot be empty"
        }
        if (emailId.isBlank()) {
            emailIdError.value = "LastName cannot be empty"
        } else if (!isValidEmail(emailId)) {
            emailIdError.value = "Please enter correct email"

        }

        if (firstNameError.value.isNullOrEmpty() && lastNameError.value.isNullOrEmpty() && emailIdError.value.isNullOrEmpty()) {
            saveUser()
            _isValidationSuccessful.value = true
        }
    }

    private fun saveUser() {
        val user = User(
            firstName = firstName,
            lastName = lastName,
            email = emailId
        )
        viewModelScope.launch {
            try {
                userRepository.insertUser(user)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return email.matches(emailRegex)
    }
}




