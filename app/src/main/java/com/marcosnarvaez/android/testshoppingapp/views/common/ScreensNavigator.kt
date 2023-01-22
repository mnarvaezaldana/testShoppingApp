package com.marcosnarvaez.android.testshoppingapp.views.common

import android.content.Context
import com.marcosnarvaez.android.testshoppingapp.views.productDetails.ProductDetailsActivity
import com.marcosnarvaez.android.testshoppingapp.views.productsList.ProductListActivity

class ScreensNavigator(private val context: Context) {

    fun toProductDetails(productId: Int) {
        ProductDetailsActivity.startProductDetailsActivity(context, productId)
    }

    fun toProductsList() {
        ProductListActivity.startProductListActivity(context)
    }
}