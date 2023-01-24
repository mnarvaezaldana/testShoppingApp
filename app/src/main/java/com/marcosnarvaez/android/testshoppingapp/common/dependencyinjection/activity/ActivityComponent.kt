package com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.activity

import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.presentation.PresentationComponent
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun newPresentationComponent(): PresentationComponent
}