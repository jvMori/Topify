package com.jvmori.topify.di.module.auth

import com.jvmori.topify.data.db.dao.AuthDao
import com.jvmori.topify.data.network.AccessToken
import com.jvmori.topify.data.repository.AuthRepository
import com.jvmori.topify.di.module.app.DatabaseModule
import com.jvmori.topify.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [DatabaseModule::class])
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

    @Provides
    @ApplicationScope
    fun provideAuthRepository(authDao: AuthDao) : AuthRepository{
        return AuthRepository(authDao)
    }
}