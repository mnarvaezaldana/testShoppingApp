package com.marcosnarvaez.android.testshoppingapp.views.productDetails

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.marcosnarvaez.android.testshoppingapp.R

class ProductsDetailsViewMvc(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup?
) {

    val rootView: View = layoutInflater.inflate(R.layout.activity_product_details, parent, false)

    private var swipeRefresh: SwipeRefreshLayout
    private var productDescriptionTV: TextView

    init {
        productDescriptionTV = findViewById(R.id.descriptionTV)
        swipeRefresh = findViewById(R.id.productSR)

        swipeRefresh.isEnabled = false
    }

    private fun <T: View?> findViewById(@IdRes id: Int): T {
        return rootView.findViewById<T>(id)
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        swipeRefresh.isRefreshing = false
    }

    fun onBindData(description: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            productDescriptionTV.text = Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            productDescriptionTV.text = Html.fromHtml(description)
        }
    }
}