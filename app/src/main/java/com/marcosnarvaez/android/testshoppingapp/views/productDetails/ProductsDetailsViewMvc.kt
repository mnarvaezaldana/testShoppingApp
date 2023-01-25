package com.marcosnarvaez.android.testshoppingapp.views.productDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.marcosnarvaez.android.testshoppingapp.R
import com.marcosnarvaez.android.testshoppingapp.products.Product
import com.marcosnarvaez.android.testshoppingapp.views.common.viewsmvc.BaseViewMvc
import com.marcosnarvaez.android.testshoppingapp.views.imageloader.ImageLoader

class ProductsDetailsViewMvc(
    layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
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
    private val productImageIV: ImageView
    private val productDescriptionTV: TextView
    private val productCategoryTV: TextView
    private val productPriceTv: TextView
    private val addToCartBTN: Button

    init {

        rootView = layoutInflater.inflate(R.layout.activity_product_details, parent, false)

        productTitleTv = findViewById(R.id.titleTV)
        productImageIV = findViewById(R.id.productIV)
        productDescriptionTV = findViewById(R.id.descriptionTV)
        productCategoryTV = findViewById(R.id.categoryTV)
        productPriceTv = findViewById(R.id.priceTV)
        addToCartBTN = findViewById(R.id.addToCartBTN)
        swipeRefresh = findViewById(R.id.productSR)

        swipeRefresh.isEnabled = false
    }

    fun bindData(product: Product) {
        productTitleTv.text = product.title
        productDescriptionTV.text = product.description
        productCategoryTV.text = context.getString(R.string.category_template, product.category)
        productPriceTv.text = context.getString(R.string.price_template, product.price)
        imageLoader.loadImage(product.image, productImageIV)
        addToCartBTN.setOnClickListener {
            for (listener in listeners) {
                listener.onProductClicked(product)
            }
        }
    }
}