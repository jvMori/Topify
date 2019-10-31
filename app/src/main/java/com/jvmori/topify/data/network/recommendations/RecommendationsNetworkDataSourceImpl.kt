package com.jvmori.topify.data.network.recommendations

import com.jvmori.topify.Utils.RecommendationsParams
import com.jvmori.topify.data.network.SpotifyApi
import com.jvmori.topify.data.response.recommendations.RecommendationsResponse
import io.reactivex.Observable
import javax.inject.Inject

class RecommendationsNetworkDataSourceImpl @Inject constructor(
    val spotifyApi: SpotifyApi
) : RecommendationsNetworkDataSource {

    override fun getRecommendations(params: RecommendationsParams): Observable<RecommendationsResponse> {
        val map = mutableMapOf<String, String>()
        params.apply {
            map["limit"] = limit.toString()
            map["market"] = market
            if (getArtistsSeed().isNotEmpty()) map["seed_artists"] = getArtistsSeed()
            if (getTracksSeed().isNotEmpty()) map["seed_tracks"] = getTracksSeed()
            if (acoustics.isNotEmpty()) map["target_acousticness"] = acoustics
            if (danceability.isNotEmpty()) map["target_danceability"] = danceability
        }


        return spotifyApi.getRecommendations(map)
    }
}