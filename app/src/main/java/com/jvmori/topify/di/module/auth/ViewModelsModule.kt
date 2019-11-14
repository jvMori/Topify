package com.jvmori.topify.di.module.auth

import androidx.lifecycle.ViewModel
import com.jvmori.topify.di.ViewModelKey
import com.jvmori.topify.di.scope.AuthActivityScope
import com.jvmori.topify.view.viewmodel.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    @AuthActivityScope
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

}