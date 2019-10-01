package com.jvmori.topify.di.module.main

import com.jvmori.topify.Utils.TOP_TARTISTS
import com.jvmori.topify.data.network.NetworkDataSource
import com.jvmori.topify.Utils.TOP_TRACKS
import com.jvmori.topify.data.db.TopifyDatabase
import com.jvmori.topify.data.db.dao.TopArtistsDao
import com.jvmori.topify.data.db.dao.TopTracksDao
import com.jvmori.topify.data.db.entity.TopArtistsResponse
import com.jvmori.topify.data.repository.BaseRepository
import com.jvmori.topify.data.repository.top.TopTracksRepository
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.data.db.entity.TopTracksResponse
import com.jvmori.topify.data.repository.top.TopArtistsRepository
import com.jvmori.topify.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class TopItemsModule {

    @Provides
    @ApplicationScope
    fun provideTopTracksDao(topifyDatabase: TopifyDatabase): TopTracksDao = topifyDatabase.topTracksDao()

    @Provides
    @ApplicationScope
    fun provideTopArtistsDao(topifyDatabase: TopifyDatabase): TopArtistsDao = topifyDatabase.topArtistsDao()

    @Provides
    @ApplicationScope
    @Named(TOP_TRACKS)
    fun provideTopTracksRepository(
        networkDataSource: NetworkDataSource,
        topTracksDao: TopTracksDao
    ): BaseRepository<TopTracksResponse, TopParam> =
        TopTracksRepository(networkDataSource, topTracksDao)

    @Provides
    @ApplicationScope
    @Named(TOP_TARTISTS)
    fun provideTopArtistsRepository(
        networkDataSource: NetworkDataSource,
        topArtistsDao: TopArtistsDao
    ): BaseRepository<TopArtistsResponse, TopParam> =
        TopArtistsRepository(networkDataSource, topArtistsDao)
}