package com.marcosnarvaez.android.testshoppingapp

import android.app.Application
import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.AppCompositionRoot

class MyApplication: Application() {

    lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {
        super.onCreate()
        appCompositionRoot = AppCompositionRoot(this)
    }
}