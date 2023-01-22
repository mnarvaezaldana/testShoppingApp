package com.marcosnarvaez.android.testshoppingapp.views.productsList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.marcosnarvaez.android.testshoppingapp.R
import com.marcosnarvaez.android.testshoppingapp.products.Product

class ProductsListViewMvc(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup?
) {

    interface Listener {
        fun onRefreshClicked()
        fun onProductClicked(productClicked: Product)
    }

    private var swipeRefresh: SwipeRefreshLayout
    private var recyclerView: RecyclerView
    private var productsAdapter: ProductsAdapter

    val rootView: View = layoutInflater.inflate(R.layout.activity_product_list, parent, false)

    private val context: Context get() = rootView.context

    private val listeners = HashSet<Listener>()

    init {

        swipeRefresh = findViewById(R.id.productsSR)
        swipeRefresh.setOnRefreshListener {
            for (listener in listeners) {
                listener.onRefreshClicked()
            }
        }

        recyclerView = findViewById(R.id.productsRV)
        recyclerView.layoutManager = LinearLayoutManager(context)
        productsAdapter = ProductsAdapter{ productClicked ->
            for (listener in listeners) {
                listener.onProductClicked(productClicked)
            }
        }
        recyclerView.adapter = productsAdapter
    }

    private fun <T: View?> findViewById(@IdRes id: Int): T {
        return rootView.findViewById<T>(id)
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }

    fun bindData(products: List<Product>) {
        productsAdapter.bindData(products)
    }

    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    class ProductsAdapter(
        private val onProductClickListener: (Product) -> Unit
    ) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

        private var productsList: List<Product> = java.util.ArrayList(0)

        inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.findViewById(R.id.txt_title)
        }

        fun bindData(products: List<Product>) {
            productsList = ArrayList(products)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_product_list_item, parent, false)
            return ProductViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            holder.title.text = productsList[position].title
            holder.itemView.setOnClickListener {
                onProductClickListener.invoke(productsList[position])
            }
        }

        override fun getItemCount(): Int {
            return productsList.size
        }

    }
}