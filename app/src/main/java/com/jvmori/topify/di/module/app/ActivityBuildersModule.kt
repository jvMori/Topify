package com.jvmori.topify.di.module.app

import com.jvmori.topify.di.module.main.MainFragmentBuildersModule
import com.jvmori.topify.view.activity.AuthorizationActivity
import com.jvmori.topify.view.activity.MainActivity
import com.jvmori.topify.view.activity.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAuthActivity() : AuthorizationActivity

    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuildersModule::class
        ]
    )
    abstract fun contributeMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract  fun contributeSplashActivity() : SplashActivity
}