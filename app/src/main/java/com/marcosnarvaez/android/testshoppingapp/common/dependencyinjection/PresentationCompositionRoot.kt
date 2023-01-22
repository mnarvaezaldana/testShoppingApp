package com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection

import com.marcosnarvaez.android.testshoppingapp.products.FetchProductDetailUseCase
import com.marcosnarvaez.android.testshoppingapp.products.FetchProductsUseCase
import com.marcosnarvaez.android.testshoppingapp.views.common.viewsmvc.ViewMvcFactory
import com.marcosnarvaez.android.testshoppingapp.views.dialogs.DialogsNavigator

class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {

    private val layoutInflater get() = activityCompositionRoot.layoutInflater

    private val fragmentManager get() = activityCompositionRoot.fragmentManager

    private val storeApi get() = activityCompositionRoot.storeApi

    private val activity get() = activityCompositionRoot.activity

    val screensNavigator get() = activityCompositionRoot.screensNavigator

    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)

    val dialogsNavigator get() = DialogsNavigator(fragmentManager)

    val fetchProductsUseCase get() = FetchProductsUseCase(storeApi)

    val fetchProductDetailUseCase get() = FetchProductDetailUseCase(storeApi)
}