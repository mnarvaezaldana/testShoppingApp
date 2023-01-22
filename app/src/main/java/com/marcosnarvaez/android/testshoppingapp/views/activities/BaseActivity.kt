package com.marcosnarvaez.android.testshoppingapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import com.marcosnarvaez.android.testshoppingapp.MyApplication
import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.ActivityCompositionRoot
import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.Injector
import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.PresentationCompositionRoot

open class BaseActivity: AppCompatActivity() {

    private val appCompositionRoot get() = (application as MyApplication).appCompositionRoot

    val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    private val compositionRoot by lazy {
        PresentationCompositionRoot(activityCompositionRoot)
    }

    protected val injector get() = Injector(compositionRoot)
}