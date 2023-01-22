package com.marcosnarvaez.android.testshoppingapp.views.productDetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.marcosnarvaez.android.testshoppingapp.MyApplication
import com.marcosnarvaez.android.testshoppingapp.products.FetchProductDetailUseCase
import com.marcosnarvaez.android.testshoppingapp.views.activities.BaseActivity
import com.marcosnarvaez.android.testshoppingapp.views.dialogs.DialogsNavigator
import kotlinx.coroutines.*

const val EXTRA_PRODUCT_ID = "productId"

class ProductDetailsActivity : BaseActivity() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var viewMvc: ProductsDetailsViewMvc
    private lateinit var fetchProductDetailUseCase: FetchProductDetailUseCase

    private lateinit var dialogsNavigator: DialogsNavigator
    private var productId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvc = ProductsDetailsViewMvc(LayoutInflater.from(this), null)
        fetchProductDetailUseCase = compositionRoot.fetchProductDetailUseCase
        dialogsNavigator = compositionRoot.dialogsNavigator
        productId = intent.extras!!.getInt(EXTRA_PRODUCT_ID, 0)
        setContentView(viewMvc.rootView)
    }

    override fun onStart() {
        super.onStart()
        fetchQuestionDetails(productId)
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchQuestionDetails(productId: Int) {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                val result = fetchProductDetailUseCase.fetchProductsDetails(productId)
                when (result) {
                    is FetchProductDetailUseCase.Result.Success -> {
                        viewMvc.bindData(result.description)
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
}