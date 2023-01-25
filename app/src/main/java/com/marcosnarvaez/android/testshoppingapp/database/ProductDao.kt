package com.marcosnarvaez.android.testshoppingapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM products_cart")
    fun getAll(): List<ProductEntity>

    @Insert
    fun insert(productEntity: ProductEntity)

    @Query("DELETE FROM products_cart")
    fun delete()
}