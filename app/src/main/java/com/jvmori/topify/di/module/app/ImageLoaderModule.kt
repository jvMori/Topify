package com.jvmori.topify.di.module.app

import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import android.app.Application
import com.jvmori.topify.Utils.ImageLoader
import com.jvmori.topify.Utils.ImageLoaderImpl
import com.jvmori.topify.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides


@Module
class ImageLoaderModule {

    @Provides
    @ApplicationScope
    fun provideImageLoader(picasso: Picasso) : ImageLoader = ImageLoaderImpl(picasso)

    @Provides
    @ApplicationScope
    fun picasso(context: Application, okHttpClient: OkHttpClient): Picasso {
        return Picasso.Builder(context.applicationContext)
            .downloader(OkHttp3Downloader(okHttpClient))
            .build()
    }
}