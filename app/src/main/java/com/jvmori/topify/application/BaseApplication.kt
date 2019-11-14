package com.jvmori.topify.application

import com.jvmori.topify.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

    val appComponent  = DaggerAppComponent
        .builder()
        .application(this)
        .build()
}