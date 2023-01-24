package com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.app

import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.activity.ActivityComponent
import com.marcosnarvaez.android.testshoppingapp.common.dependencyinjection.activity.ActivityModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent
}