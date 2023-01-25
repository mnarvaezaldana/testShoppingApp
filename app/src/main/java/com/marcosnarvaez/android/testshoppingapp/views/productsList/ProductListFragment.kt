package com.marcosnarvaez.android.testshoppingapp.views.productsList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcosnarvaez.android.testshoppingapp.products.FetchProductsUseCase
import com.marcosnarvaez.android.testshoppingapp.products.Product
import com.marcosnarvaez.android.testshoppingapp.views.common.ScreensNavigator
import com.marcosnarvaez.android.testshoppingapp.views.common.viewsmvc.ViewMvcFactory
import com.marcosnarvaez.android.testshoppingapp.views.dialogs.DialogsNavigator
import com.marcosnarvaez.android.testshoppingapp.views.fragments.BaseFragment
import kotlinx.coroutines.*
import javax.inject.Inject

class ProductListFragment : BaseFragment(), ProductsListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val TAG = ProductListFragment::class.java.name
    private var isDataLoaded = false

    @Inject lateinit var fetchProductsUseCase: FetchProductsUseCase
    @Inject lateinit var dialogsNavigator: DialogsNavigator
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var viewMvcFactory: ViewMvcFactory

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
        viewMvc.hideDeleteButton()
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

    override fun onDelete() {
        Log.e(TAG, "not Implemented yet $TAG")
    }

}