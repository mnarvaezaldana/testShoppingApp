package com.marcosnarvaez.android.testshoppingapp.views

import android.os.Bundle
import com.marcosnarvaez.android.testshoppingapp.products.FetchDataLogin
import com.marcosnarvaez.android.testshoppingapp.views.activities.BaseActivity
import com.marcosnarvaez.android.testshoppingapp.views.common.ScreensNavigator
import com.marcosnarvaez.android.testshoppingapp.views.common.viewsmvc.ViewMvcFactory
import javax.inject.Inject

class LogInActivity : BaseActivity(), ProductsLoginViewMvc.Listener {

    @Inject lateinit var fetchDataLogin: FetchDataLogin
    @Inject lateinit var viewMvcFactory: ViewMvcFactory
    @Inject lateinit var screensNavigator: ScreensNavigator
    private lateinit var viewMvc: ProductsLoginViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        viewMvc = viewMvcFactory.newProductLoginViewMvc(null)
        setContentView(viewMvc.rootView)
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        if (fetchDataLogin.checkedLogIn()) {
            moveToProductsListActivity()
        }
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
    }

    override fun onLogIn() {
        fetchDataLogin.logIn(true)
        moveToProductsListActivity()
    }

    private fun moveToProductsListActivity() {
        screensNavigator.toProductsList()
    }
}