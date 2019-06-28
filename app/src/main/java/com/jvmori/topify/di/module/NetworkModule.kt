package com.jvmori.topify.di.module

import com.jvmori.topify.Utils.MyServiceInterceptor
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
    fun provideAccessToken(): String {
        return AccessToken.accessToken
    }

    @Provides
    @ApplicationScope
    fun provideMySessionInterceptor(): MyServiceInterceptor {
        return MyServiceInterceptor()
    }

    //    @Provides
//    @ApplicationScope
//    fun provideInterceptor(myServiceInterceptor: MyServiceInterceptor): MyServiceInterceptor {
////        return Interceptor { chain ->
////            val url = chain.request()
////                .url()
////                .newBuilder()
////                .build()
////
////            val request = chain.request()
////                .newBuilder()
////                .addHeader("Authorization", "Bearer $accessToken")
////                .url(url)
////                .build()
////            return@Interceptor chain.proceed(request)
//        return MyServiceInterceptor()
//        }
//    }
    @Provides
    @ApplicationScope
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }


    @Provides
    @ApplicationScope
    fun okHttpClient(interceptor: MyServiceInterceptor, okHttpClientBuilder: OkHttpClient.Builder): OkHttpClient {
        return okHttpClientBuilder
            .addInterceptor(interceptor)
            .retryOnConnectionFailure(true)
            .build()
    }
}