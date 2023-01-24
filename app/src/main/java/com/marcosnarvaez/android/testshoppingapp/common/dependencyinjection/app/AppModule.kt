package com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.app

import android.app.Application
import androidx.annotation.UiThread
import androidx.room.Database
import androidx.room.Room
import com.marcosnarvaez.android.testshoppingapp.database.ProductsDatabase
import com.marcosnarvaez.android.testshoppingapp.networking.StoreApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@UiThread
@Module
class AppModule(val application: Application) {

    @Provides
    @AppScope
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

     @Provides
     @AppScope
     fun retrofit(okHttpClient: OkHttpClient): Retrofit  {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @AppScope
    fun database(): ProductsDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            ProductsDatabase::class.java,
            "products_cart")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun application() = application

    @Provides
    @AppScope
    fun storeApi(retrofit: Retrofit): StoreApi = retrofit.create(StoreApi::class.java)
}