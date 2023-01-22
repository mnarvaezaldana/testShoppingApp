package com.marcosnarvaez.android.testshoppingapp.views.productsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcosnarvaez.android.testshoppingapp.R
import com.marcosnarvaez.android.testshoppingapp.products.Product
import com.marcosnarvaez.android.testshoppingapp.views.common.viewsmvc.BaseViewMvc

class ProductsListViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
): BaseViewMvc<ProductsListViewMvc.Listener>(
    layoutInflater,
    parent,
    R.layout.activity_product_list) {

    interface Listener {
        fun onRefreshClicked()
        fun onProductClicked(productClicked: Product)
    }

    private var recyclerView: RecyclerView
    private var productsAdapter: ProductsAdapter

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


    fun bindData(products: List<Product>) {
        productsAdapter.bindData(products)
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