package com.marcosnarvaez.android.testshoppingapp.views.productsList

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.marcosnarvaez.android.testshoppingapp.networking.StoreApi
import com.marcosnarvaez.android.testshoppingapp.products.Product
import com.marcosnarvaez.android.testshoppingapp.views.dialogs.ServerErrorDialogFragment
import com.marcosnarvaez.android.testshoppingapp.views.productDetails.ProductDetailsActivity.Companion.startProductDetailsActivity
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ProductListActivity : AppCompatActivity(), ProductsListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var storeApi: StoreApi

    private var isDataLoaded = false

    private lateinit var viewMvc: ProductsListViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewMvc = ProductsListViewMvc(LayoutInflater.from(this), null)

        setContentView(viewMvc.rootView)

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClient.readTimeout(30, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(30, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
        storeApi = retrofit.create(StoreApi::class.java)
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
                val response = storeApi.products()
                if (response.isSuccessful && response.body() != null) {
                    viewMvc.bindData(response.body()!!)
                    isDataLoaded = true
                } else {
                    onFetchFailed()
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    onFetchFailed()
                }
            }finally {
                viewMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        supportFragmentManager.beginTransaction()
            .add(ServerErrorDialogFragment.newInstance(), null)
            .commitAllowingStateLoss()
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
        startProductDetailsActivity(this, productClicked.id)
    }

}