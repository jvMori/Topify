package com.jvmori.topify.di.module.app

import com.jvmori.topify.data.network.AccessToken
import com.jvmori.topify.data.network.MyServiceInterceptor
import com.jvmori.topify.di.module.auth.AuthModule
import okhttp3.OkHttpClient
import com.jvmori.topify.di.scope.ApplicationScope
import com.jvmori.topify.di.scope.AuthActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class NetworkModule {

    @ApplicationScope
    @Provides
    @Named("Access_Token")
    fun provideAccessToken() : String {
        return AccessToken().accessToken
    }

    @Provides
    @ApplicationScope
    fun provideMySessionInterceptor(@Named("Access_Token") accessToken: String): MyServiceInterceptor {
        return MyServiceInterceptor(accessToken)
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    @ApplicationScope
    fun okHttpClient(interceptor: MyServiceInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .retryOnConnectionFailure(true)
            .build()
    }
}