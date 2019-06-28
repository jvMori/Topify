package com.jvmori.topify.di.module.main

import dagger.Module
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jvmori.topify.di.ViewModelKey
import com.jvmori.topify.di.scope.ApplicationScope
import com.jvmori.topify.view.viewmodel.AuthViewModel
import com.jvmori.topify.view.viewmodel.DiscoverViewModel
import com.jvmori.topify.view.viewmodel.ViewModelFactory
import dagger.multibindings.IntoMap
import dagger.Binds

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DiscoverViewModel::class)
    abstract fun bindDiscoverViewModel(discoverViewModel: DiscoverViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(modelProviderFactory: ViewModelFactory): ViewModelProvider.Factory
}