package com.jvmori.topify.di.module.auth

import com.jvmori.topify.data.network.AccessToken
import com.jvmori.topify.di.scope.ApplicationScope
import com.jvmori.topify.di.scope.AuthActivityScope
import dagger.Module
import dagger.Provides
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