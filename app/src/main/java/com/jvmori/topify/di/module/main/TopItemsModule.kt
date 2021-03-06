package com.jvmori.topify.di.module.main

import com.jvmori.topify.Utils.TOP_ARTISTS
import com.jvmori.topify.Utils.TOP_PARAMS
import com.jvmori.topify.data.network.NetworkDataSource
import com.jvmori.topify.Utils.TOP_TRACKS
import com.jvmori.topify.data.db.TopifyDatabase
import com.jvmori.topify.data.db.dao.TopArtistsDao
import com.jvmori.topify.data.db.dao.TopTracksDao
import com.jvmori.topify.data.db.entity.TopArtistsResponse
import com.jvmori.topify.data.db.entity.TopTracksResponse
import com.jvmori.topify.data.repository.top.*
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.di.scope.ApplicationScope
import com.jvmori.topify.di.scope.MainActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class TopItemsModule {

    @Provides
    @MainActivityScope
    fun provideTopTracksDao(topifyDatabase: TopifyDatabase): TopTracksDao = topifyDatabase.topTracksDao()

    @Provides
    @MainActivityScope
    fun provideTopArtistsDao(topifyDatabase: TopifyDatabase): TopArtistsDao = topifyDatabase.topArtistsDao()

    @Provides
    @MainActivityScope
    @Named(TOP_TRACKS)
    fun provideTopTracksRepository(
        networkDataSource: NetworkDataSource,
        topTracksDao: TopTracksDao
    ): TopRepository<TopTracksResponse> =
        TopTracksRepository(networkDataSource, topTracksDao)

    @Provides
    @MainActivityScope
    @Named(TOP_ARTISTS)
    fun provideTopArtistsRepository(
        networkDataSource: NetworkDataSource,
        topArtistsDao: TopArtistsDao
    ): TopRepository<TopArtistsResponse> =
        TopArtistsRepository(networkDataSource, topArtistsDao)

    @Provides
    @MainActivityScope
    fun provideTopParamsRepository(
        topTracksDao: TopTracksDao
    ): TopParamsRepository =
        TopParamsRepositoryImpl(topTracksDao)
}