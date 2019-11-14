package com.jvmori.topify.di.module.auth

import com.jvmori.topify.data.db.TopifyDatabase
import com.jvmori.topify.data.db.dao.AuthDao
import com.jvmori.topify.data.network.AccessToken
import com.jvmori.topify.data.repository.AuthRepository
import com.jvmori.topify.di.module.app.DatabaseModule
import com.jvmori.topify.di.scope.ApplicationScope
import com.jvmori.topify.di.scope.AuthActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AuthModule {

    @AuthActivityScope
    @Provides
    @Named("REDIRECT_URI")
    fun provideRedirectUri() : String {
        return  "jvmori://topify"
    }

    @Provides
    @AuthActivityScope
    fun provideAuthDao(topifyDatabase: TopifyDatabase) : AuthDao = topifyDatabase.authDao()

    @Provides
    @AuthActivityScope
    fun provideAuthRepository(authDao: AuthDao) : AuthRepository{
        return AuthRepository(authDao)
    }
}