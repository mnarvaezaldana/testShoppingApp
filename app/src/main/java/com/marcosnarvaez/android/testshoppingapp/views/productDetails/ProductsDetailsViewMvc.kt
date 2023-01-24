package com.marcosnarvaez.android.testshoppingapp.views.productDetails

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.marcosnarvaez.android.testshoppingapp.R
import com.marcosnarvaez.android.testshoppingapp.products.Product
import com.marcosnarvaez.android.testshoppingapp.views.common.viewsmvc.BaseViewMvc
import org.w3c.dom.Text

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

    private var productTitleTv: TextView
    private var productDescriptionTV: TextView
    private val productCategoryTV: TextView
    private var productPriceTv: TextView

    init {

        rootView = layoutInflater.inflate(R.layout.activity_product_details, parent, false)

        productTitleTv = findViewById(R.id.titleTV)
        productDescriptionTV = findViewById(R.id.descriptionTV)
        productCategoryTV = findViewById(R.id.categoryTV)
        productPriceTv = findViewById(R.id.priceTV)
        swipeRefresh = findViewById(R.id.productSR)

        swipeRefresh.isEnabled = false
    }



    fun bindData(product: Product) {
        productTitleTv.text = product.title
        productDescriptionTV.text = product.description
        productCategoryTV.text = context.getString(R.string.category_template, product.category)
        productPriceTv.text = context.getString(R.string.price_template, product.price)
    }
}