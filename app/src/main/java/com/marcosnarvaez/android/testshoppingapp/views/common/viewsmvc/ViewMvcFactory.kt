package com.marcosnarvaez.android.testshoppingapp.views.common.viewsmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.marcosnarvaez.android.testshoppingapp.views.productDetails.ProductsDetailsViewMvc
import com.marcosnarvaez.android.testshoppingapp.views.productsList.ProductsListViewMvc
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(private val layoutInflater: LayoutInflater) {

    fun newProductsListViewMvc(parent: ViewGroup?): ProductsListViewMvc {
        return ProductsListViewMvc(layoutInflater, parent)
    }

    fun newProductDetailsViewMvc(parent: ViewGroup?): ProductsDetailsViewMvc {
        return ProductsDetailsViewMvc(layoutInflater, parent)
    }
}