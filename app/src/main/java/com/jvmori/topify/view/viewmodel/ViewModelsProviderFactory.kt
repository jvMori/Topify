package com.jvmori.topify.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jvmori.topify.di.scope.ApplicationScope
import dagger.Reusable
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Reusable
class ViewModelFactory @Inject constructor(
    private val viewModels: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModels[modelClass]?.get() as T
    }
}

