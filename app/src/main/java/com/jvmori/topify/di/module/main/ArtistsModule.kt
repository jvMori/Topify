package com.jvmori.topify.di.module.main

import com.jvmori.topify.data.network.artists.ArtistsNetworkDataSource
import com.jvmori.topify.data.network.artists.ArtistsNetworkDataSourceImpl
import com.jvmori.topify.data.repository.artists.ArtistsRepository
import com.jvmori.topify.data.repository.artists.ArtistsRepositoryImpl
import com.jvmori.topify.di.scope.ApplicationScope
import dagger.Binds
import dagger.Module

@Module
abstract class ArtistsModule {

    @ApplicationScope
    @Binds
    abstract fun bindNetworkDataSource(networkDataSource: ArtistsNetworkDataSourceImpl) : ArtistsNetworkDataSource

    @ApplicationScope
    @Binds
    abstract fun bindRepository(repository : ArtistsRepositoryImpl) : ArtistsRepository

}