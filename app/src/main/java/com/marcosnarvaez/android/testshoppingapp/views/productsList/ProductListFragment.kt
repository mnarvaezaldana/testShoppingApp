package com.marcosnarvaez.android.testshoppingapp.views.productsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.Service
import com.marcosnarvaez.android.testshoppingapp.products.FetchProductsUseCase
import com.marcosnarvaez.android.testshoppingapp.products.Product
import com.marcosnarvaez.android.testshoppingapp.views.common.ScreensNavigator
import com.marcosnarvaez.android.testshoppingapp.views.common.viewsmvc.ViewMvcFactory
import com.marcosnarvaez.android.testshoppingapp.views.dialogs.DialogsNavigator
import com.marcosnarvaez.android.testshoppingapp.views.fragments.BaseFragment
import kotlinx.coroutines.*

class ProductListFragment : BaseFragment(), ProductsListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private var isDataLoaded = false

    @field:Service private lateinit var fetchProductsUseCase: FetchProductsUseCase
    @field:Service private lateinit var dialogsNavigator: DialogsNavigator
    @field:Service private lateinit var screensNavigator: ScreensNavigator
    @field:Service private lateinit var viewMvcFactory: ViewMvcFactory

    private lateinit var viewMvc: ProductsListViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewMvc = viewMvcFactory.newProductsListViewMvc(container)
        return viewMvc.rootView
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

    override fun onRefreshClicked() {
        fetchProducts()
    }

    override fun onProductClicked(productClicked: Product) {
        screensNavigator.toProductDetails(productClicked.id)
    }

}