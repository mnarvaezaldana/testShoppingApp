package com.marcosnarvaez.android.testshoppingapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import com.marcosnarvaez.android.testshoppingapp.MyApplication
import com.marcosnarvaez.android.testshoppingapp.common.composition.ActivityCompositionRoot

open class BaseActivity: AppCompatActivity() {

    private val appCompositionRoot get() = (application as MyApplication).appCompositionRoot

    val compositionRoot by lazy { ActivityCompositionRoot(this, appCompositionRoot) }
}