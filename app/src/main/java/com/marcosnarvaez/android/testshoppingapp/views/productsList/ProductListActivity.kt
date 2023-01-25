package com.marcosnarvaez.android.testshoppingapp.views.productsList

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.marcosnarvaez.android.testshoppingapp.R
import com.marcosnarvaez.android.testshoppingapp.products.FetchDataLogin
import com.marcosnarvaez.android.testshoppingapp.views.activities.BaseActivity

class ProductListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_frame)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_layout, ProductListFragment())
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.cart -> { supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, ProductsCartListFragment())
                .commit()
                true
            }
            R.id.logOut -> {
                logOut()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logOut() {
        try {
            val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLogged", false)
            editor.apply()
        } catch (t: Throwable) {
            throw t
        }
    }
}