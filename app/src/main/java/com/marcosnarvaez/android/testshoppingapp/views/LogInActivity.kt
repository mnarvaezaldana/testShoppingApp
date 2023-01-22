package com.marcosnarvaez.android.testshoppingapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.marcosnarvaez.android.testshoppingapp.R
import com.marcosnarvaez.android.testshoppingapp.views.common.ScreensNavigator
import com.marcosnarvaez.android.testshoppingapp.views.productsList.ProductListActivity

class LogInActivity : AppCompatActivity() {

    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screensNavigator = ScreensNavigator(this)
        findViewById<Button>(R.id.LoginBTN).setOnClickListener {
            screensNavigator.toProductsList()
        }
    }
}