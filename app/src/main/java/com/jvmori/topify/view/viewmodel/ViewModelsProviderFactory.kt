package com.jvmori.topify.view.viewmodel

import javax.inject.Inject
import javax.inject.Provider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jvmori.topify.di.scope.ApplicationScope

@Suppress("UNCHECKED_CAST")
@ApplicationScope
class ViewModelFactory @Inject
constructor(
    private val creators: MutableMap<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("unknown model class " + modelClass)
        }
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}