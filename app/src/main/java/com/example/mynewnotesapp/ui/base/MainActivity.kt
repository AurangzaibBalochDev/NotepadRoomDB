package com.example.mynewnotesapp.ui.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mynewnotesapp.di.sharedModules
import org.koin.compose.KoinApplication

val LocalNavHostController = compositionLocalOf<NavHostController> {
    error("")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            CompositionLocalProvider(LocalNavHostController provides navController) {

                MyNavHost()

            }

//Ab check kr WO phly wo app uninstall kr
        }

    }
}


