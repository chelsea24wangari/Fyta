package com.chelsea.fyta


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.chelsea.fyta.ui.navigations.AppNavHost
import com.chelsea.fyta.ui.theme.FytaTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FytaTheme {
                AppNavHost()
            }
        }
    }
}