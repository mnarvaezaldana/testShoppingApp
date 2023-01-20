package com.marcosnarvaez.android.testshoppingapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ProductListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
    }

    companion object {
        fun startProductListActivity(context: Context) {
            val intent = Intent(context, ProductListActivity::class.java)
            context.startActivity(intent)
        }
    }
}