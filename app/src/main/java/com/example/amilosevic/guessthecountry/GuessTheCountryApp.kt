package com.example.amilosevic.guessthecountry

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.amilosevic.guessthecountry.di.appModule
import com.example.amilosevic.guessthecountry.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GuessTheCountryApp : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        startKoin {
            androidContext(this@GuessTheCountryApp)
            modules(appModule, viewModelModule)
        }
    }
}