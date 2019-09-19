package com.jvmori.topify.di.module.main

import com.jvmori.topify.data.network.NetworkDataSource
import com.jvmori.topify.di.module.app.ApiModule
import com.jvmori.topify.di.scope.MainActivityScope
import com.jvmori.topify.Utils.TOP_TRACKS
import com.jvmori.topify.data.db.TopifyDatabase
import com.jvmori.topify.data.db.dao.AuthDao
import com.jvmori.topify.data.db.dao.TopTracksDao
import com.jvmori.topify.data.repository.BaseRepository
import com.jvmori.topify.data.repository.TopTracksRepository
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.data.db.entity.TopTracksResponse
import com.jvmori.topify.di.module.app.DataSourceModule
import com.jvmori.topify.di.module.app.DatabaseModule
import com.jvmori.topify.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class TopTracksModule {

    @Provides
    @ApplicationScope
    fun provideTopTracksDao(topifyDatabase: TopifyDatabase) : TopTracksDao = topifyDatabase.topTracksDao()

    @Provides
    @ApplicationScope
    @Named(TOP_TRACKS)
    fun provideTopTracksRepository(networkDataSource : NetworkDataSource, topTracksDao: TopTracksDao) : BaseRepository<TopTracksResponse, TopParam> =
        TopTracksRepository(networkDataSource, topTracksDao)
}