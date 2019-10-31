package com.jvmori.topify.data.repository

import com.jvmori.topify.data.db.entity.TopArtistsResponse
import com.jvmori.topify.data.network.NetworkDataSource
import com.jvmori.topify.data.response.playlist.AddTracks
import com.jvmori.topify.data.response.playlist.AddTracksResponse
import com.jvmori.topify.data.response.playlist.PlaylistResponse
import com.jvmori.topify.data.response.search.ArtistsResponse
import com.jvmori.topify.data.response.top.Image
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.data.response.user.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : IRepository
{
    override fun searchArtist(query: String) : Observable<ArtistsResponse>{
        return networkDataSource.searchArtists(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun getCurrentUser() : Observable<User>{
        return networkDataSource.getCurrentUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun getTopArtists(param: TopParam): Observable<TopArtistsResponse> {
       return networkDataSource.getTopArtists(param)
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeOn(Schedulers.io())
    }

    override fun createPlaylist(userId: String, playlistName: String, playlistDescription : String): Observable<PlaylistResponse> {
        return networkDataSource.createPlaylist(userId, playlistName, playlistDescription)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun addTracksToPlaylist(playlistId: String, tracks: AddTracks): Observable<AddTracksResponse> {
        return networkDataSource.addTracksToPlaylist(playlistId, tracks)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun getPlaylistCoverImage(playlistId: String): Observable<List<Image>> {
        return networkDataSource.getPlaylistCoverImage(playlistId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}