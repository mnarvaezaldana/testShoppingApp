package com.marcosnarvaez.android.testshoppingapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 2)
abstract class ProductsDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

}