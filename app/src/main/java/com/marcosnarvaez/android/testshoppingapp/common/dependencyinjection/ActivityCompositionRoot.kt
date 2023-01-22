package com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.marcosnarvaez.android.testshoppingapp.views.common.ScreensNavigator

class ActivityCompositionRoot(
    val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
    ) {

    val screensNavigator by lazy {
        ScreensNavigator(activity.baseContext)
    }

    val application get() = appCompositionRoot.application

    val layoutInflater get() = LayoutInflater.from(activity)

    val fragmentManager get() = activity.supportFragmentManager

    val storeApi get() = appCompositionRoot.storeApi
}