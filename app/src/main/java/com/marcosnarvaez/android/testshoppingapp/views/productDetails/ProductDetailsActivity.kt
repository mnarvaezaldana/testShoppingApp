package com.marcosnarvaez.android.testshoppingapp.views.productDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.marcosnarvaez.android.testshoppingapp.products.FetchProductDetailUseCase
import com.marcosnarvaez.android.testshoppingapp.products.Product
import com.marcosnarvaez.android.testshoppingapp.views.activities.BaseActivity
import com.marcosnarvaez.android.testshoppingapp.views.common.ScreensNavigator
import com.marcosnarvaez.android.testshoppingapp.views.common.viewsmvc.ViewMvcFactory
import com.marcosnarvaez.android.testshoppingapp.views.dialogs.DialogsNavigator
import kotlinx.coroutines.*
import javax.inject.Inject

const val EXTRA_PRODUCT_ID = "productId"

class ProductDetailsActivity : BaseActivity(), ProductsDetailsViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    @Inject lateinit var fetchProductDetailUseCase: FetchProductDetailUseCase
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var dialogsNavigator: DialogsNavigator
    @Inject lateinit var viewMvcFactory: ViewMvcFactory
    private lateinit var viewMvc: ProductsDetailsViewMvc
    private var productId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        viewMvc = viewMvcFactory.newProductDetailsViewMvc(null)
        setContentView(viewMvc.rootView)

        productId = intent.extras!!.getInt(EXTRA_PRODUCT_ID, 0)
    }

    override fun onStart() {
        super.onStart()
        fetchProductDetails(productId)
        viewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        viewMvc.unregisterListener(this)
    }

    private fun fetchProductDetails(productId: Int) {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                val result = fetchProductDetailUseCase.fetchProductsDetails(productId)
                when (result) {
                    is FetchProductDetailUseCase.Result.Success -> {
                        viewMvc.bindData(result.product)
                    }
                    is FetchProductDetailUseCase.Result.Failure -> {
                        onFetchFailed()
                    }
                }
            } finally {
                viewMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    companion object {
        fun startProductDetailsActivity(context: Context, productId: Int) {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra(EXTRA_PRODUCT_ID, productId)
            context.startActivity(intent)
        }
    }

    override fun onRefreshClicked() {
        Log.e("message", "not implemented yet")
    }

    override fun onProductClicked(productClicked: Product) {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                fetchProductDetailUseCase.addProductToCart(productClicked)
            } finally {
                viewMvc.hideProgressIndication()
            }
        }
    }
}