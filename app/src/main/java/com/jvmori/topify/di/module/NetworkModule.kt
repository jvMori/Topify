package com.jvmori.topify.di.module

import com.jvmori.topify.data.network.AccessToken
import okhttp3.OkHttpClient
import com.jvmori.topify.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
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
    fun provideInterceptor(@Named("Access_Token") accessToken : String): Interceptor {
        return Interceptor { chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .build()

            val request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }
    }


    @Provides
    @ApplicationScope
    fun okHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .retryOnConnectionFailure(true)
            .build()
    }
}