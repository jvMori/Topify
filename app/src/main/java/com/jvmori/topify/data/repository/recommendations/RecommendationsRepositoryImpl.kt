package com.jvmori.topify.data.repository.recommendations

import com.jvmori.topify.Utils.RecommendationsParams
import com.jvmori.topify.data.network.recommendations.RecommendationsNetworkDataSource
import com.jvmori.topify.data.response.recommendations.RecommendationsResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RecommendationsRepositoryImpl @Inject constructor(
    val networkDataSource : RecommendationsNetworkDataSource
): RecommendationsRepository {
    override fun getRecommendations(params: RecommendationsParams): Observable<RecommendationsResponse> {
        return networkDataSource.getRecommendations(params)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}