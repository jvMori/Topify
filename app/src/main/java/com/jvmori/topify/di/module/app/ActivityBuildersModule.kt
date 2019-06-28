package com.jvmori.topify.di.module.app

import com.jvmori.topify.di.module.auth.AuthModule
import com.jvmori.topify.view.activity.AuthorizationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAuthActivity() : AuthorizationActivity
}