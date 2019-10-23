package com.jvmori.topify.data.network.recommendations

import com.jvmori.topify.data.response.recommendations.RecommendationsResponse
import io.reactivex.Observable

interface RecommendationsNetworkDataSource {
    fun getRecommendations() : Observable<RecommendationsResponse>
}