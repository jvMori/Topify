package com.jvmori.topify.di.module.main

import com.jvmori.topify.data.network.recommendations.RecommendationsNetworkDataSource
import com.jvmori.topify.data.network.recommendations.RecommendationsNetworkDataSourceImpl
import com.jvmori.topify.data.repository.recommendations.RecommendationsRepository
import com.jvmori.topify.data.repository.recommendations.RecommendationsRepositoryImpl
import com.jvmori.topify.di.scope.ApplicationScope
import dagger.Binds
import dagger.Module

@Module
abstract class RecommendationsModule {

    @ApplicationScope
    @Binds
    abstract fun bindRecommendationsNetworkDataSource(
        recommendationsNetworkDataSourceImpl: RecommendationsNetworkDataSourceImpl
    ): RecommendationsNetworkDataSource

    @ApplicationScope
    @Binds
    abstract fun bindRecommendationsRepository(
        recommendationsRepository: RecommendationsRepositoryImpl
    ) : RecommendationsRepository
}