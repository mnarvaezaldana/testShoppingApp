package com.marcosnarvaez.android.testshoppingapp.common.composition

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.marcosnarvaez.android.testshoppingapp.products.FetchProductDetailUseCase
import com.marcosnarvaez.android.testshoppingapp.products.FetchProductsUseCase
import com.marcosnarvaez.android.testshoppingapp.views.common.ScreensNavigator
import com.marcosnarvaez.android.testshoppingapp.views.dialogs.DialogsNavigator

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
    ) {

    val screensNavigator by lazy {
        ScreensNavigator(activity.baseContext)
    }

    private val fragmentManager get() = activity.supportFragmentManager

    val dialogsNavigator get() = DialogsNavigator(fragmentManager)

    private val storeApi get() = appCompositionRoot.storeApi

    val fetchProductsUseCase get() = FetchProductsUseCase(storeApi)

    val fetchProductDetailUseCase get() = FetchProductDetailUseCase(storeApi)
}