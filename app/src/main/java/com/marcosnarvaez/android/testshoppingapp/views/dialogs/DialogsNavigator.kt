package com.marcosnarvaez.android.testshoppingapp.views.dialogs

import androidx.fragment.app.FragmentManager

class DialogsNavigator(private val fragmentManager: FragmentManager) {

    fun showServerErrorDialog() {
        fragmentManager.beginTransaction()
            .add(ServerErrorDialogFragment.newInstance(), null)
            .commitAllowingStateLoss()
    }
}