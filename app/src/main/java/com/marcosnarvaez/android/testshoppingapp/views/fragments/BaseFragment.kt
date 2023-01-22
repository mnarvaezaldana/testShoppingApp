package com.marcosnarvaez.android.testshoppingapp.views.fragments

import androidx.fragment.app.Fragment
import com.marcosnarvaez.android.testshoppingapp.common.composition.PresentationCompositionRoot
import com.marcosnarvaez.android.testshoppingapp.views.activities.BaseActivity

open class BaseFragment: Fragment() {

    protected val compositionRoot by lazy {
        PresentationCompositionRoot((requireActivity() as BaseActivity).activityCompositionRoot)
    }

}