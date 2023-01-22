package com.marcosnarvaez.android.testshoppingapp.views.fragments

import androidx.fragment.app.Fragment
import com.marcosnarvaez.android.testshoppingapp.views.activities.BaseActivity

open class BaseFragment: Fragment() {

    protected val compositionRoot get() = (requireActivity() as BaseActivity).compositionRoot
}