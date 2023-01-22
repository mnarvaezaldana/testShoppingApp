package com.marcosnarvaez.android.testshoppingapp.views.productDetails

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.marcosnarvaez.android.testshoppingapp.R
import com.marcosnarvaez.android.testshoppingapp.products.Product
import com.marcosnarvaez.android.testshoppingapp.views.common.viewsmvc.BaseViewMvc

class ProductsDetailsViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
): BaseViewMvc<ProductsDetailsViewMvc.Listener>(
    layoutInflater,
    parent,
    R.layout.activity_product_details) {

    interface Listener {
        fun onRefreshClicked()
        fun onProductClicked(productClicked: Product)
    }

    private var productDescriptionTV: TextView

    init {

        rootView = layoutInflater.inflate(R.layout.activity_product_details, parent, false)

        productDescriptionTV = findViewById(R.id.descriptionTV)
        swipeRefresh = findViewById(R.id.productSR)

        swipeRefresh.isEnabled = false
    }



    fun bindData(description: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            productDescriptionTV.text = Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            productDescriptionTV.text = Html.fromHtml(description)
        }
    }
}