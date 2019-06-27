package com.jvmori.topify.di.module

import com.jvmori.topify.data.Repository
import com.jvmori.topify.data.network.NetworkDataSource
import com.jvmori.topify.data.network.NetworkDataSourceImpl
import com.jvmori.topify.data.network.SpotifyApi
import com.jvmori.topify.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class])
class DataSourceModule {

    @Provides
    @ApplicationScope
    fun repository(networkDataSource : NetworkDataSource) : Repository = Repository(networkDataSource)

    @Provides
    @ApplicationScope
    fun provideNetworkDataSource(spotifyApi: SpotifyApi) : NetworkDataSource = NetworkDataSourceImpl(spotifyApi)
}