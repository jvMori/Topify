package com.jvmori.topify.data.network.artists

import com.jvmori.topify.data.network.SpotifyApi
import com.jvmori.topify.data.response.search.ArtistsAlbumsResponse
import io.reactivex.Observable
import javax.inject.Inject

class ArtistsNetworkDataSourceImpl @Inject constructor(
    val api : SpotifyApi
) : ArtistsNetworkDataSource {
    override fun fetchArtistsAlbums(id: String): Observable<ArtistsAlbumsResponse> {
       return api.getArtistsAlbums(id)
    }
}