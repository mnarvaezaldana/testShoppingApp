package com.marcosnarvaez.android.testshoppingapp.networking

import com.marcosnarvaez.android.testshoppingapp.products.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {

    @GET
    suspend fun products(): Response<ProductsListResponseSchema>

    @GET("/{productId}")
    suspend fun questionDetails(@Path("productId") productId: String?): Response<Product>

}