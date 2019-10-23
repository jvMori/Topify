package com.jvmori.topify.data.network.recommendations

import com.jvmori.topify.Utils.RecommendationsParams
import com.jvmori.topify.data.network.SpotifyApi
import com.jvmori.topify.data.response.recommendations.RecommendationsResponse
import io.reactivex.Observable
import javax.inject.Inject

class RecommendationsNetworkDataSourceImpl @Inject constructor(
    val spotifyApi: SpotifyApi
): RecommendationsNetworkDataSource {

    override fun getRecommendations(params : RecommendationsParams): Observable<RecommendationsResponse> {
        val map = mutableMapOf<String, String>()
        map["limit"] = params.limit.toString()
        map["market"] = params.market
        map["seed_artists"] = params.getArtistsSeed()
        map["seed_tracks"] = params.getTracksSeed()
        map["target_acousticness"] = params.acoustics
        map["target_danceability"] = params.danceability

        return spotifyApi.getRecommendations(map)
    }
}