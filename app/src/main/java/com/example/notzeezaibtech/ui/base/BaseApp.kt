package com.example.notzeezaibtech.ui.base

import android.app.Application
import com.example.notzeezaibtech.di.sharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(sharedModules)
        }
    }
}
