package com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.presentation

import com.marcosnarvaez.android.testshoppingapp.views.LogInActivity
import com.marcosnarvaez.android.testshoppingapp.views.productDetails.ProductDetailsActivity
import com.marcosnarvaez.android.testshoppingapp.views.productsList.ProductListFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent
interface PresentationComponent {

    fun inject(fragment: ProductListFragment)
    fun inject(productDetailsActivity: ProductDetailsActivity)
    fun inject(logInActivity: LogInActivity)
}