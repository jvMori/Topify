package com.jvmori.topify.data.network

import com.jvmori.topify.data.response.search.Artists
import com.jvmori.topify.data.response.top.TopArtistsResponse
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.data.response.top.TopTracksResponse
import com.jvmori.topify.data.response.user.User
import io.reactivex.Observable
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    val api: SpotifyApi
) : NetworkDataSource {

    override fun searchArtists(query: String): Observable<List<Artists>> {
        return api.search(query, "artist")
    }

    override fun getCurrentUser(): Observable<User> {
        return api.getUser()
    }

    override fun getTopArtists(param: TopParam): Observable<TopArtistsResponse> {
        val map = createMap(param)
        return api.getTopArtists(map)
    }

    override fun getTopTracks(param: TopParam): Observable<TopTracksResponse> {
        val map = createMap(param)
        return api.getTopTracks(map)
    }

    private fun createMap(param: TopParam): HashMap<String, String> {
        val map = hashMapOf<String, String>()
        map["limit"] = param.limit.toString()
        map["time_range"] = param.timeRange
        return map
    }
}