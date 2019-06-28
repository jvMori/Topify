package com.jvmori.topify.di.module

import com.jvmori.topify.di.module.auth.AuthModule
import com.jvmori.topify.view.activity.AuthorizationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
           // AuthModule::class
        ]
    )
    abstract fun contributeAuthActivity() : AuthorizationActivity
}