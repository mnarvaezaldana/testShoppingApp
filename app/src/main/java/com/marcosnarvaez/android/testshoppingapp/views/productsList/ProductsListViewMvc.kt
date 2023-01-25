package com.marcosnarvaez.android.testshoppingapp.views.productsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.marcosnarvaez.android.testshoppingapp.R
import com.marcosnarvaez.android.testshoppingapp.products.Product
import com.marcosnarvaez.android.testshoppingapp.views.common.viewsmvc.BaseViewMvc
import com.marcosnarvaez.android.testshoppingapp.views.imageloader.ImageLoader

class ProductsListViewMvc(
    layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    parent: ViewGroup?
): BaseViewMvc<ProductsListViewMvc.Listener>(
    layoutInflater,
    parent,
    R.layout.activity_product_list) {

    interface Listener {
        fun onRefreshClicked()
        fun onProductClicked(productClicked: Product)
        fun onDelete()
    }

    private var recyclerView: RecyclerView
    private var productsAdapter: ProductsAdapter
    private var deleteItems: FloatingActionButton

    init {
        swipeRefresh = findViewById(R.id.productsSR)
        swipeRefresh.setOnRefreshListener {
            for (listener in listeners) {
                listener.onRefreshClicked()
            }
        }

        deleteItems = findViewById(R.id.deleteCartFAB)
        deleteItems.setOnClickListener {
            for (listener in listeners) {
                listener.onDelete()
            }
        }

        recyclerView = findViewById(R.id.productsRV)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        productsAdapter = ProductsAdapter(imageLoader) { productClicked ->
            for (listener in listeners) {
                listener.onProductClicked(productClicked)
            }
        }
        recyclerView.adapter = productsAdapter
    }


    fun bindData(products: List<Product>) {
        productsAdapter.bindData(products)
    }

    fun hideDeleteButton() {
        deleteItems.visibility = View.GONE
    }

    class ProductsAdapter(
        private val imageLoader: ImageLoader,
        private val onProductClickListener: (Product) -> Unit
    ) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

        private var productsList: List<Product> = java.util.ArrayList(0)

        inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.findViewById(R.id.titleTV)
            val description: TextView = view.findViewById(R.id.descriptionTV)
            val image: ImageView = view.findViewById(R.id.productIV)
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
            imageLoader.loadImage(productsList[position].image, holder.image)
            holder.title.text = productsList[position].title
            holder.description.text = productsList[position].description
            holder.itemView.setOnClickListener {
                onProductClickListener.invoke(productsList[position])
            }
        }

        override fun getItemCount(): Int {
            return productsList.size
        }

    }
}