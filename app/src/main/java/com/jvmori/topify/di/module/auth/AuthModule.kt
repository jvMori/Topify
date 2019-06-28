package com.jvmori.topify.di.module.auth

import androidx.lifecycle.ViewModel
import com.jvmori.topify.data.network.AccessToken
import com.jvmori.topify.di.ViewModelKey
import com.jvmori.topify.di.scope.ApplicationScope
import com.jvmori.topify.di.scope.AuthActivityScope
import com.jvmori.topify.view.viewmodel.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
class AuthModule {

    @ApplicationScope
    @Provides
    @Named("Access_Token")
    fun provideAccessToken() : String {
        return AccessToken().accessToken
    }

    @ApplicationScope
    @Provides
    @Named("REDIRECT_URI")
    fun provideRedirectUri() : String {
        return  "jvmori://topify"
    }
}