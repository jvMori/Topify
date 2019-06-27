package com.jvmori.topify.di.module.main

import dagger.Module
import androidx.lifecycle.ViewModel
import com.jvmori.topify.di.ViewModelKey
import com.jvmori.topify.view.viewmodel.DiscoverViewModel
import dagger.multibindings.IntoMap
import dagger.Binds

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(DiscoverViewModel::class)
    abstract fun bindDiscoverViewModel(discoverViewModel: DiscoverViewModel): ViewModel
}