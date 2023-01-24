package com.marcosnarvaez.android.testshoppingapp.views.common.viewsmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.marcosnarvaez.android.testshoppingapp.database.ProductsDatabase
import com.marcosnarvaez.android.testshoppingapp.views.imageloader.ImageLoader
import com.marcosnarvaez.android.testshoppingapp.views.productDetails.ProductsDetailsViewMvc
import com.marcosnarvaez.android.testshoppingapp.views.productsList.ProductsListViewMvc
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(
    private val layoutInflater: LayoutInflater,
    private val database: ProductsDatabase,
    private val imageLoader: ImageLoader) {

    fun newProductsListViewMvc(parent: ViewGroup?): ProductsListViewMvc {
        return ProductsListViewMvc(layoutInflater, imageLoader, parent)
    }

    fun newProductDetailsViewMvc(parent: ViewGroup?): ProductsDetailsViewMvc {
        return ProductsDetailsViewMvc(layoutInflater, imageLoader, database, parent)
    }
}