package com.marcosnarvaez.android.testshoppingapp.views

import android.os.Bundle
import android.widget.Button
import com.marcosnarvaez.android.testshoppingapp.R
import com.marcosnarvaez.android.testshoppingapp.views.activities.BaseActivity
import com.marcosnarvaez.android.testshoppingapp.views.common.ScreensNavigator

class LogInActivity : BaseActivity() {

    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screensNavigator = compositionRoot.screensNavigator
        findViewById<Button>(R.id.LoginBTN).setOnClickListener {
            screensNavigator.toProductsList()
        }
    }
}