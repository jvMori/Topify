package com.jvmori.topify.di.module.main

import androidx.lifecycle.ViewModel
import com.jvmori.topify.di.ViewModelKey
import com.jvmori.topify.view.viewmodel.CreateTopViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(CreateTopViewModel::class)
    abstract fun bindCreateTopViewModel(createTopViewModel: CreateTopViewModel): ViewModel
}