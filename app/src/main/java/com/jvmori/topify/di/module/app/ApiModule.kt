package com.jvmori.topify.di.module.app

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.jvmori.topify.data.network.SpotifyApi
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import com.jvmori.topify.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides


@Module(includes = [NetworkModule::class])
class ApiModule {

    companion object {
        private const val BASE_URL = "https://api.spotify.com/v1/"
    }

    @Provides
    @ApplicationScope
    fun spotifyApi(retrofit: Retrofit): SpotifyApi {
        return retrofit.create<SpotifyApi>(SpotifyApi::class.java)
    }

    @Provides
    @ApplicationScope
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

}