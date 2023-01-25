package com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.presentation

import com.marcosnarvaez.android.testshoppingapp.views.productLogin.LogInActivity
import com.marcosnarvaez.android.testshoppingapp.views.productDetails.ProductDetailsActivity
import com.marcosnarvaez.android.testshoppingapp.views.productsList.ProductListFragment
import com.marcosnarvaez.android.testshoppingapp.views.productsList.ProductsCartListFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent
interface PresentationComponent {

    fun inject(fragment: ProductListFragment)
    fun inject(fragment: ProductsCartListFragment)
    fun inject(productDetailsActivity: ProductDetailsActivity)
    fun inject(logInActivity: LogInActivity)
}