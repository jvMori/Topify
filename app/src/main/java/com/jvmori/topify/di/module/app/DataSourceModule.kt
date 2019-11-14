package com.jvmori.topify.di.module.app

import com.jvmori.topify.data.repository.IRepository
import com.jvmori.topify.data.repository.Repository
import com.jvmori.topify.data.network.NetworkDataSource
import com.jvmori.topify.data.network.NetworkDataSourceImpl
import com.jvmori.topify.data.network.SpotifyApi
import com.jvmori.topify.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    @ApplicationScope
    fun repository(networkDataSource : NetworkDataSource) : IRepository =
        Repository(networkDataSource)


    @Provides
    @ApplicationScope
    fun provideNetworkDataSource(spotifyApi: SpotifyApi) : NetworkDataSource = NetworkDataSourceImpl(spotifyApi)

}