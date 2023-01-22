package com.marcosnarvaez.android.testshoppingapp.views.common

import android.content.Context
import android.content.Intent
import com.marcosnarvaez.android.testshoppingapp.views.productDetails.ProductDetailsActivity
import com.marcosnarvaez.android.testshoppingapp.views.productsList.ProductListActivity

class ScreensNavigator(private val context: Context) {

    fun toProductDetails(productId: Int) {
        ProductDetailsActivity.startProductDetailsActivity(context, productId)
    }

    fun toProductsList() {
        val intent = Intent(context, ProductListActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}