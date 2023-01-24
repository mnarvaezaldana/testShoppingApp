package com.marcosnarvaez.android.testshoppingapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import com.marcosnarvaez.android.testshoppingapp.MyApplication
import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.activity.ActivityModule
import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.activity.ActivityComponent

open class BaseActivity: AppCompatActivity() {

    private val appComponent get() = (application as MyApplication).appComponent

    val activityComponent: ActivityComponent by lazy {
        appComponent.newActivityComponent(ActivityModule(this))
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent()
    }

    protected val injector get() = presentationComponent
}