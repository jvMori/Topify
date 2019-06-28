package com.jvmori.topify.di.module.main

import com.jvmori.topify.view.fragment.FragmentHome
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contibuteHomeFragment() : FragmentHome
}