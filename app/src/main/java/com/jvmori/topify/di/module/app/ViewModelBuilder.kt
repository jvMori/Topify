package com.jvmori.topify.di.module.app

import androidx.lifecycle.ViewModelProvider
import com.jvmori.topify.view.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
abstract class ViewModelBuilder {

    @Binds
    @Reusable
    internal abstract fun bindViewModelFactory(modelProviderFactory: ViewModelFactory): ViewModelProvider.Factory
}