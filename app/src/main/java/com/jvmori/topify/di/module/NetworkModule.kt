package com.jvmori.topify.di.module

import com.jvmori.topify.data.network.MyServiceInterceptor
import com.jvmori.topify.data.network.AccessToken
import okhttp3.OkHttpClient
import com.jvmori.topify.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    @Named("Access_Token")
    fun provideAccessToken() : String {
        return AccessToken.accessToken
    }

    @Provides
    @ApplicationScope
    fun provideMySessionInterceptor(): MyServiceInterceptor {
        return MyServiceInterceptor()
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