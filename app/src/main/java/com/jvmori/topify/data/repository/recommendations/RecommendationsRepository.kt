package com.jvmori.topify.data.repository.recommendations

import com.jvmori.topify.Utils.RecommendationsParams
import com.jvmori.topify.data.response.recommendations.RecommendationsResponse
import com.jvmori.topify.di.scope.MainActivityScope
import io.reactivex.Observable

@MainActivityScope
interface RecommendationsRepository {
    fun getRecommendations(params : RecommendationsParams): Observable<RecommendationsResponse>
}