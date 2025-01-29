package com.example.mynewnotesapp.ui.base

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mynewnotesapp.R

val LocalNavHostController = compositionLocalOf<NavHostController> {
    error("NavHostController not provided!")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CompositionLocalProvider(LocalNavHostController provides navController) {
                MyNavHost()

            }
        }
    }
}