package com.marcosnarvaez.android.testshoppingapp.views.fragments

import androidx.fragment.app.Fragment
import com.marcosnarvaez.android.testshoppingapp.views.activities.BaseActivity

open class BaseFragment: Fragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent()
    }

    protected val injector get() = presentationComponent

}