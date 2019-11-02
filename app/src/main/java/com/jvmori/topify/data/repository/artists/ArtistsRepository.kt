package com.jvmori.topify.data.repository.artists

import com.jvmori.topify.data.response.search.ArtistsAlbumsResponse
import io.reactivex.Observable

interface ArtistsRepository {
    fun fetchArtistsAlbums(id : String) : Observable<ArtistsAlbumsResponse>
}