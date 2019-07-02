package com.jvmori.topify.di.module.main

import com.jvmori.topify.di.scope.MainActivityScope
import com.jvmori.topify.view.fragment.FragmentCreateTop
import com.jvmori.topify.view.fragment.FragmentHome
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @MainActivityScope
    @ContributesAndroidInjector
    abstract fun contibuteHomeFragment() : FragmentHome

    @MainActivityScope
    @ContributesAndroidInjector
    abstract fun contributeCreateTopFrament() : FragmentCreateTop
}