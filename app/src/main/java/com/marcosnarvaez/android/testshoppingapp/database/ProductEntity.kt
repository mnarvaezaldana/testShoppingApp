package com.marcosnarvaez.android.testshoppingapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_cart")
 class ProductEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "price")
    val price: String = "",

    @ColumnInfo(name = "category")
    val category: String = "",

    @ColumnInfo(name = "description")
    val description: String = "",

    @ColumnInfo(name = "image")
    val image: String = ""
)
