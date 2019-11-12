package com.jvmori.topify.di.module.main

import com.jvmori.topify.data.network.artists.ArtistsNetworkDataSource
import com.jvmori.topify.data.network.artists.ArtistsNetworkDataSourceImpl
import com.jvmori.topify.data.repository.artists.ArtistsRepository
import com.jvmori.topify.data.repository.artists.ArtistsRepositoryImpl
import com.jvmori.topify.di.scope.ApplicationScope
import com.jvmori.topify.di.scope.MainActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class ArtistsModule {

    @MainActivityScope
    @Binds
    abstract fun bindNetworkDataSource(networkDataSource: ArtistsNetworkDataSourceImpl) : ArtistsNetworkDataSource

    @MainActivityScope
    @Binds
    abstract fun bindRepository(repository : ArtistsRepositoryImpl) : ArtistsRepository

}