package com.jvmori.topify.di.module.app

import androidx.lifecycle.ViewModelProvider
import com.jvmori.topify.di.scope.ApplicationScope
import com.jvmori.topify.view.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelBuilder {
    @Binds
    @ApplicationScope
    internal abstract fun bindViewModelFactory(modelProviderFactory: ViewModelFactory): ViewModelProvider.Factory
}