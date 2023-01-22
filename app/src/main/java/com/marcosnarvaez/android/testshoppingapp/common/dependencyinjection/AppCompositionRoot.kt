package com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection

import android.app.Application
import androidx.annotation.UiThread
import com.marcosnarvaez.android.testshoppingapp.networking.StoreApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@UiThread
class AppCompositionRoot(val application: Application) {

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val storeApi: StoreApi by lazy {
        retrofit.create(StoreApi::class.java)
    }
}