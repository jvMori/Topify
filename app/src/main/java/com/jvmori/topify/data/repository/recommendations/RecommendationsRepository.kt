package com.jvmori.topify.data.repository.recommendations

import com.jvmori.topify.Utils.RecommendationsParams
import com.jvmori.topify.data.response.recommendations.RecommendationsResponse
import io.reactivex.Observable

interface RecommendationsRepository {
    fun getRecommendations(params : RecommendationsParams): Observable<RecommendationsResponse>
}