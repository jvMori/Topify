package com.jvmori.topify.data.network

import com.jvmori.topify.data.response.Artists
import io.reactivex.Observable
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    val api: SpotifyApi
) : NetworkDataSource {

    override fun searchArtists(query: String): Observable<List<Artists>> {
       return api.search(query)
    }
}