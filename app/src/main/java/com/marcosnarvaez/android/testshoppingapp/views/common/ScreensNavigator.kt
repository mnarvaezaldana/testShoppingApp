package com.marcosnarvaez.android.testshoppingapp.views.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.activity.ActivityScope
import com.marcosnarvaez.android.testshoppingapp.views.productDetails.EXTRA_PRODUCT_ID
import com.marcosnarvaez.android.testshoppingapp.views.productDetails.ProductDetailsActivity
import com.marcosnarvaez.android.testshoppingapp.views.productsList.ProductListActivity

@ActivityScope
class ScreensNavigator (private val appCompatActivity: AppCompatActivity) {

    fun toProductDetails(productId: Int) {
        val intent = Intent(appCompatActivity, ProductDetailsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(EXTRA_PRODUCT_ID, productId)
        appCompatActivity.startActivity(intent)
    }

    fun toProductsList() {
        val intent = Intent(appCompatActivity, ProductListActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        appCompatActivity.startActivity(intent)
    }
}