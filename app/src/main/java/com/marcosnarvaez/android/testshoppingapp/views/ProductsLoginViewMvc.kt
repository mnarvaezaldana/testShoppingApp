package com.marcosnarvaez.android.testshoppingapp.views

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.marcosnarvaez.android.testshoppingapp.R
import com.marcosnarvaez.android.testshoppingapp.views.common.viewsmvc.BaseViewMvc

class ProductsLoginViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
): BaseViewMvc<ProductsLoginViewMvc.Listener>(
    layoutInflater,
    parent,
    R.layout.activity_main) {

    interface Listener {
        fun onLogIn()
    }

    private val loginBtn: Button
    private val usernameET: EditText
    private val passwordET: EditText

    init {
        rootView = layoutInflater.inflate(R.layout.activity_main, parent, false)
        loginBtn = findViewById(R.id.LoginBTN)
        usernameET = findViewById(R.id.UsernameET)
        passwordET = findViewById(R.id.PasswordET)

        loginBtn.setOnClickListener {
            listenerOnLogin()
        }
        usernameET.addTextChangedListener {
            setButtonEnable()
        }
        passwordET.addTextChangedListener {
            setButtonEnable()
        }
    }

    fun clearInputs() {
        usernameET.text.clear()
        passwordET.text.clear()
    }

    private fun listenerOnLogin() {
        for (listener in listeners) {
            listener.onLogIn()
        }
    }

    private fun setButtonEnable() {
        loginBtn.isEnabled = (usernameET.text.length >= 5 && passwordET.text.length >= 5)
    }

}