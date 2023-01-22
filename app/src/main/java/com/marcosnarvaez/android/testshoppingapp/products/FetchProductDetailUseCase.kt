package com.marcosnarvaez.android.testshoppingapp.products

import com.marcosnarvaez.android.testshoppingapp.networking.StoreApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchProductDetailUseCase(private val storeApi: StoreApi) {

    sealed class Result {
        class Success(val description: String): Result()
        object Failure: Result()
    }

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