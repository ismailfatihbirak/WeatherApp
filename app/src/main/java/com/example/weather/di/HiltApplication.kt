package com.example.weather.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication:Application() {

    companion object {
        lateinit var instance : HiltApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}