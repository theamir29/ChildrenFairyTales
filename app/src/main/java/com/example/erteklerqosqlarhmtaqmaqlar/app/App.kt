package com.example.erteklerqosqlarhmtaqmaqlar.app

import android.app.Application
import com.example.erteklerqosqlarhmtaqmaqlar.di.appModule
import com.example.erteklerqosqlarhmtaqmaqlar.di.dataModule
import com.example.erteklerqosqlarhmtaqmaqlar.di.domainModule
import com.example.erteklerqosqlarhmtaqmaqlar.di.localModule
import com.example.erteklerqosqlarhmtaqmaqlar.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(appModule, dataModule, domainModule, localModule, networkModule))

            androidContext(this@App)
        }
    }
}