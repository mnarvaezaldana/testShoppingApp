package com.marcosnarvaez.android.testshoppingapp.products

import com.marcosnarvaez.android.testshoppingapp.database.ProductEntity
import com.marcosnarvaez.android.testshoppingapp.database.ProductsDatabase
import com.marcosnarvaez.android.testshoppingapp.networking.StoreApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchProductDetailUseCase @Inject constructor(
    private val database: ProductsDatabase,
    private val storeApi: StoreApi) {

    sealed class Result {
        data class Success(val product: Product): Result()
        object Failure: Result()
    }

    suspend fun fetchProductsDetails(productId: Int): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = storeApi.productsDetails(productId)
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

    suspend fun addProductToCart(product: Product): Result {
        return withContext(Dispatchers.IO) {
            try {
                val responseT = database.productDao()
                val productEntity = ProductEntity(
                    product.id,
                    product.title,
                    product.price,
                    product.category,
                    product.description,
                    product.image)
                responseT.insert(productEntity)
                return@withContext Result.Success(product)
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