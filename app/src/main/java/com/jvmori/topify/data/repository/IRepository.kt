package com.jvmori.topify.data.repository

import com.jvmori.topify.data.db.entity.TopArtistsResponse
import com.jvmori.topify.data.response.playlist.AddTracks
import com.jvmori.topify.data.response.playlist.AddTracksResponse
import com.jvmori.topify.data.response.playlist.PlaylistResponse
import com.jvmori.topify.data.response.search.ArtistsResponse
import com.jvmori.topify.data.response.top.Image
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.data.response.user.User
import io.reactivex.Observable

interface IRepository {
    fun searchArtist(query: String) : Observable<ArtistsResponse>
    fun getCurrentUser() : Observable<User>
    fun getTopArtists(param: TopParam) : Observable<TopArtistsResponse>
    fun createPlaylist(userId: String, playlistName : String, playlistDescription : String) : Observable<PlaylistResponse>
    fun addTracksToPlaylist(playlistId : String, tracks : AddTracks) : Observable<AddTracksResponse>
    fun getPlaylistCoverImage(playlistId: String) : Observable<List<Image>>
}