package com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection

import com.marcosnarvaez.android.testshoppingapp.products.FetchProductDetailUseCase
import com.marcosnarvaez.android.testshoppingapp.products.FetchProductsUseCase
import com.marcosnarvaez.android.testshoppingapp.views.common.ScreensNavigator
import com.marcosnarvaez.android.testshoppingapp.views.common.viewsmvc.ViewMvcFactory
import com.marcosnarvaez.android.testshoppingapp.views.dialogs.DialogsNavigator
import java.lang.reflect.Field

class Injector(private val presentationCompositionRoot: PresentationCompositionRoot) {

    fun inject(client: Any) {
        for (field in getAllFields(client)) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field)
            }
        }
    }

    private fun getAllFields(client: Any): Array<out Field> {
        val clientClass = client::class.java
        return  clientClass.declaredFields
    }

    private fun isAnnotatedForInjection(field: Field): Boolean {
        val fieldAnnotations = field.annotations
        for (annotation in fieldAnnotations) {
            if (annotation is Service) {
                return true
            }
        }
        return false
    }

    private fun injectField(client: Any, field: Field) {
        val isAccessibleInitially = field.isAccessible
        field.isAccessible = true
        field.set(client, getServiceForClass(field.type))
        field.isAccessible = isAccessibleInitially
    }

    private fun getServiceForClass(type: Class<*>): Any {
        when (type) {
            DialogsNavigator::class.java -> {
                return presentationCompositionRoot.dialogsNavigator
            }
            ScreensNavigator::class.java -> {
                return presentationCompositionRoot.screensNavigator
            }
            FetchProductsUseCase::class.java -> {
                return presentationCompositionRoot.fetchProductsUseCase
            }
            FetchProductDetailUseCase::class.java -> {
                return presentationCompositionRoot.fetchProductDetailUseCase
            }
            ViewMvcFactory::class.java -> {
                return presentationCompositionRoot.viewMvcFactory
            }
            else -> {
                throw Exception("unsupported service type: $type")
            }
        }
    }
}