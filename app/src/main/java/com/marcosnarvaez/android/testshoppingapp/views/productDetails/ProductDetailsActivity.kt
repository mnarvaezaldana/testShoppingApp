package com.marcosnarvaez.android.testshoppingapp.views.productDetails

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.marcosnarvaez.android.testshoppingapp.R
import com.marcosnarvaez.android.testshoppingapp.networking.StoreApi
import com.marcosnarvaez.android.testshoppingapp.views.dialogs.ServerErrorDialogFragment
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val EXTRA_PRODUCT_ID = "productId"

class ProductDetailsActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)


    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var productDescriptionTV: TextView

    private lateinit var storeApi: StoreApi

    private var productId: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        productId = intent.extras!!.getInt(EXTRA_PRODUCT_ID, 0)

        productDescriptionTV = findViewById(R.id.descriptionTV)

        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.isEnabled = false

        // init retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        storeApi = retrofit.create(StoreApi::class.java)
    }

    override fun onStart() {
        super.onStart()
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            showProgressIndication()
            try {
                val response = storeApi.productsDetails(productId)
                if (response.isSuccessful && response.body() != null) {
                    val questionBody = response.body()!!
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        productDescriptionTV.text = Html.fromHtml(questionBody.toString(), Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        @Suppress("DEPRECATION")
                        productDescriptionTV.text = Html.fromHtml(questionBody.toString())
                    }
                } else {
                    onFetchFailed()
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    onFetchFailed()
                }
            } finally {
                hideProgressIndication()
            }

        }
    }


    private fun onFetchFailed() {
        supportFragmentManager.beginTransaction()
            .add(ServerErrorDialogFragment.newInstance(), null)
            .commitAllowingStateLoss()
    }

    private fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    private fun hideProgressIndication() {
        swipeRefresh.isRefreshing = false
    }

    companion object {
        fun startProductDetailsActivity(context: Context, productId: Int) {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT_ID, productId)
            context.startActivity(intent)
        }
    }
}