package com.marcosnarvaez.android.testshoppingapp.views.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.activity.ActivityScope
import com.marcosnarvaez.android.testshoppingapp.views.productDetails.ProductDetailsActivity
import com.marcosnarvaez.android.testshoppingapp.views.productsList.ProductListActivity

@ActivityScope
class ScreensNavigator (private val appCompatActivity: AppCompatActivity) {

    fun toProductDetails(productId: Int) {
        ProductDetailsActivity.startProductDetailsActivity(appCompatActivity, productId)
    }

    fun toProductsList() {
        val intent = Intent(appCompatActivity.baseContext, ProductListActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        appCompatActivity.baseContext.startActivity(intent)
    }
}