package com.jvmori.topify.data.repository.artists

import com.jvmori.topify.data.network.artists.ArtistsNetworkDataSource
import com.jvmori.topify.data.response.search.ArtistsAlbumsResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArtistsRepositoryImpl @Inject constructor(
    val networkDataSource : ArtistsNetworkDataSource
): ArtistsRepository {
    override fun fetchArtistsAlbums(id: String): Observable<ArtistsAlbumsResponse> {
       return networkDataSource.fetchArtistsAlbums(id)
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeOn(Schedulers.io())
    }
}