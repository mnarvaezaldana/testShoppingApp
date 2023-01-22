package com.marcosnarvaez.android.testshoppingapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import com.marcosnarvaez.android.testshoppingapp.MyApplication

open class BaseActivity: AppCompatActivity() {

    val compositionRoot get() = (application as MyApplication).appCompositionRoot
}