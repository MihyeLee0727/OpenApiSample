package com.example.openapisample.presentation

import android.app.Application
import com.example.openapisample.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OpenApiSampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@OpenApiSampleApplication)
            modules(appModules)
        }
    }
}