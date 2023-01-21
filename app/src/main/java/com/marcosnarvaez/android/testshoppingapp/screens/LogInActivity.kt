package com.marcosnarvaez.android.testshoppingapp.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.marcosnarvaez.android.testshoppingapp.R
import com.marcosnarvaez.android.testshoppingapp.screens.productsList.ProductListActivity

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.LoginBTN).setOnClickListener {
            ProductListActivity.startProductListActivity(this)
        }
    }
}