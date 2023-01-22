package com.marcosnarvaez.android.testshoppingapp.views.productsList

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.marcosnarvaez.android.testshoppingapp.MyApplication
import com.marcosnarvaez.android.testshoppingapp.products.FetchProductsUseCase
import com.marcosnarvaez.android.testshoppingapp.products.Product
import com.marcosnarvaez.android.testshoppingapp.views.common.ScreensNavigator
import com.marcosnarvaez.android.testshoppingapp.views.dialogs.DialogsNavigator
import com.marcosnarvaez.android.testshoppingapp.views.dialogs.ServerErrorDialogFragment
import com.marcosnarvaez.android.testshoppingapp.views.productDetails.ProductDetailsActivity.Companion.startProductDetailsActivity
import kotlinx.coroutines.*

class ProductListActivity : AppCompatActivity(), ProductsListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)


    private var isDataLoaded = false

    private lateinit var viewMvc: ProductsListViewMvc

    private lateinit var fetchProductsUseCase: FetchProductsUseCase

    private lateinit var dialogsNavigator: DialogsNavigator

    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewMvc = ProductsListViewMvc(LayoutInflater.from(this), null)
        fetchProductsUseCase = FetchProductsUseCase((application as MyApplication).storeApi)
        dialogsNavigator = DialogsNavigator(supportFragmentManager)
        screensNavigator = ScreensNavigator(this)
        setContentView(viewMvc.rootView)
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        if (!isDataLoaded) {
            fetchProducts()
        }
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        viewMvc.unregisterListener(this)
    }

    private fun fetchProducts() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                val result = fetchProductsUseCase.fetchProducts()
                when (result) {
                    is FetchProductsUseCase.Result.Success -> {
                        viewMvc.bindData(result.products)
                        isDataLoaded = true
                    }
                    is FetchProductsUseCase.Result.Failure -> {
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
        fun startProductListActivity(context: Context) {
            val intent = Intent(context, ProductListActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onRefreshClicked() {
        fetchProducts()
    }

    override fun onProductClicked(productClicked: Product) {
        screensNavigator.toProductDetails(productClicked.id)
    }

}