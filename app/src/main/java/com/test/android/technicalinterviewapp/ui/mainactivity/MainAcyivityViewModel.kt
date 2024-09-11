package com.test.android.technicalinterviewapp.ui.mainactivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.android.technicalinterviewapp.data.local.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainAcyivityViewModel @Inject constructor(private val preferencesManager: PreferencesManager) :ViewModel( ){
    // Using mutableStateOf instead of MutableLiveData
    var isLoggedIn by mutableStateOf(false)
        private set

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        isLoggedIn = preferencesManager.isLoggedIn()
    }
}