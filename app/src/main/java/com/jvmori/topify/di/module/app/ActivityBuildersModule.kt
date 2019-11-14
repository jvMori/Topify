package com.jvmori.topify.di.module.app

import com.jvmori.topify.di.module.auth.AuthModule
import com.jvmori.topify.di.module.auth.ViewModelsModule
import com.jvmori.topify.di.module.main.*
import com.jvmori.topify.di.scope.AuthActivityScope
import com.jvmori.topify.di.scope.MainActivityScope
import com.jvmori.topify.view.activity.AuthorizationActivity
import com.jvmori.topify.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthActivityScope
    @ContributesAndroidInjector(
        modules = [
            AuthModule::class,
            ViewModelsModule::class
        ]
    )
    abstract fun contributeAuthActivity(): AuthorizationActivity

    @MainActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuildersModule::class,
            TopItemsModule::class,
            RecommendationsModule::class,
            ArtistsModule::class,
            MainViewModelsModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}