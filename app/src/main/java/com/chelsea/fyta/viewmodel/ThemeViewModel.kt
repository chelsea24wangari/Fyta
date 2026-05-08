package com.chelsea.fyta.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {

    var darkModeEnabled by mutableStateOf(false)
        private set

    fun toggleDarkMode(enabled: Boolean) {
        darkModeEnabled = enabled
    }
}