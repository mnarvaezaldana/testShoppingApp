package com.marcosnarvaez.android.testshoppingapp.products

import com.marcosnarvaez.android.testshoppingapp.networking.StoreApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FetchProductDetailUseCase {

    sealed class Result {
        class Success(val description: String): Result()
        object Failure: Result()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var storeApi = retrofit.create(StoreApi::class.java)

    suspend fun fetchProductsDetails(productId: Int): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = storeApi.productsDetails(productId)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.description)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }

}