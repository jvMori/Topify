package com.jvmori.topify.data.network.artists

import com.jvmori.topify.data.response.search.ArtistsAlbumsResponse
import io.reactivex.Observable

interface ArtistsNetworkDataSource {
    fun fetchArtistsAlbums(id : String) : Observable<ArtistsAlbumsResponse>
}