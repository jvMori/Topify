package com.jvmori.topify.di.module.app

import com.jvmori.topify.Utils.TOP_TRACKS
import com.jvmori.topify.data.db.TopifyDatabase
import com.jvmori.topify.data.db.dao.TopTracksDao
import com.jvmori.topify.data.db.entity.TopTracksResponse
import com.jvmori.topify.data.repository.IRepository
import com.jvmori.topify.data.repository.Repository
import com.jvmori.topify.data.network.NetworkDataSource
import com.jvmori.topify.data.network.NetworkDataSourceImpl
import com.jvmori.topify.data.network.SpotifyApi
import com.jvmori.topify.data.repository.BaseRepository
import com.jvmori.topify.data.repository.TopTracksRepository
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.di.scope.ApplicationScope
import com.jvmori.topify.di.scope.MainActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [ApiModule::class])
class DataSourceModule {

    @Provides
    @ApplicationScope
    fun repository(networkDataSource : NetworkDataSource) : IRepository =
        Repository(networkDataSource)


    @Provides
    @ApplicationScope
    fun provideNetworkDataSource(spotifyApi: SpotifyApi) : NetworkDataSource = NetworkDataSourceImpl(spotifyApi)

}