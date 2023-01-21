package com.marcosnarvaez.android.testshoppingapp.screens.productsList

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.marcosnarvaez.android.testshoppingapp.R
import com.marcosnarvaez.android.testshoppingapp.networking.StoreApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductListActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var storeApi: StoreApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        storeApi = retrofit.create(StoreApi::class.java)

        coroutineScope.launch {
            val response = storeApi.products()
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.forEach { Log.e("data", it.title) }
            }
        }
    }

    companion object {
        fun startProductListActivity(context: Context) {
            val intent = Intent(context, ProductListActivity::class.java)
            context.startActivity(intent)
        }
    }
}