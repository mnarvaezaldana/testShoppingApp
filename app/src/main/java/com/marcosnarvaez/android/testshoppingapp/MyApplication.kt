package com.marcosnarvaez.android.testshoppingapp

import android.app.Application
import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.app.AppComponent
import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.app.AppModule
import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.app.DaggerAppComponent

class MyApplication: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}