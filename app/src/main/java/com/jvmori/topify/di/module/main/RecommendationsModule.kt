package com.jvmori.topify.di.module.main

import com.jvmori.topify.data.network.SpotifyApi
import com.jvmori.topify.data.network.recommendations.RecommendationsNetworkDataSource
import com.jvmori.topify.data.network.recommendations.RecommendationsNetworkDataSourceImpl
import com.jvmori.topify.data.repository.recommendations.RecommendationsRepository
import com.jvmori.topify.data.repository.recommendations.RecommendationsRepositoryImpl
import com.jvmori.topify.di.scope.MainActivityScope
import dagger.Module
import dagger.Provides

@Module
class RecommendationsModule {

    @MainActivityScope
    @Provides
    fun provideRecommendationsNetworkDataSource(spotifyApi: SpotifyApi): RecommendationsNetworkDataSource =
        RecommendationsNetworkDataSourceImpl(
            spotifyApi
        )

    @MainActivityScope
    @Provides
    fun bindRecommendationsRepository(recommendationsNetworkDataSource: RecommendationsNetworkDataSource): RecommendationsRepository =
        RecommendationsRepositoryImpl(
            recommendationsNetworkDataSource
        )
}