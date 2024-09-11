package com.test.android.technicalinterviewapp.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferencesManager  @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // Keys for shared preferences
    companion object {
        private const val PREFS_NAME = "my_prefs"
        private const val KEY_LOGGED_IN = "logged_in"

        @Volatile
        private var instance: PreferencesManager? = null

        fun getInstance(context: Context): PreferencesManager {
            return instance ?: synchronized(this) {
                instance ?: PreferencesManager(context).also { instance = it }
            }
        }
    }

    // Function to save login state
    fun setLoggedIn(loggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_LOGGED_IN, loggedIn).apply()
    }

    // Function to get login state
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_LOGGED_IN, false)
    }
}
