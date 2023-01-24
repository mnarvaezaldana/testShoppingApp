package com.marcosnarvaez.android.testshoppingapp.views

import android.os.Bundle
import android.widget.Button
import com.marcosnarvaez.android.testshoppingapp.R
import com.marcosnarvaez.android.testshoppingapp.views.activities.BaseActivity
import com.marcosnarvaez.android.testshoppingapp.views.common.ScreensNavigator
import javax.inject.Inject

class LogInActivity : BaseActivity() {

    @Inject lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.LoginBTN).setOnClickListener {
            screensNavigator.toProductsList()
        }
    }
}