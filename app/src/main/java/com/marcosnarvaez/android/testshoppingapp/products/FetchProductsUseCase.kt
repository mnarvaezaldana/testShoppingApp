package com.marcosnarvaez.android.testshoppingapp.products

import com.marcosnarvaez.android.testshoppingapp.networking.StoreApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchProductsUseCase @Inject constructor(private val storeApi: StoreApi) {

    sealed class Result {
        data class Success(val products: List<Product>): Result()
        object Failure: Result()
    }

    suspend fun fetchProducts(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = storeApi.products()
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!)
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