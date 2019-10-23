package com.jvmori.topify.data.network.recommendations

import com.jvmori.topify.data.network.SpotifyApi
import com.jvmori.topify.data.response.recommendations.RecommendationsResponse
import io.reactivex.Observable
import javax.inject.Inject

class RecommendationsNetworkDataSourceImpl @Inject constructor(
    val spotifyApi: SpotifyApi
): RecommendationsNetworkDataSource {

    override fun getRecommendations(): Observable<RecommendationsResponse> {
        return spotifyApi.getRecommendations()
    }
}