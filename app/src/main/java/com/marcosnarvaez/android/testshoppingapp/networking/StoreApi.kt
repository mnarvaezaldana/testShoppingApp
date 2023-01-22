package com.marcosnarvaez.android.testshoppingapp.networking

import com.marcosnarvaez.android.testshoppingapp.products.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {

    @GET("products/")
    suspend fun products(): Response<List<Product>>

    @GET("products/{productId}")
    suspend fun productsDetails(@Path("productId") productId: Int?): Response<Product>

}