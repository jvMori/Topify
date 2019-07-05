package com.jvmori.topify.data.network

import com.jvmori.topify.data.response.playlist.PlaylistResponse
import com.jvmori.topify.data.response.search.Artists
import com.jvmori.topify.data.response.top.TopArtistsResponse
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.data.response.top.TopTracksResponse
import com.jvmori.topify.data.response.user.User
import io.reactivex.Observable

interface NetworkDataSource {
    fun searchArtists(query: String) : Observable<List<Artists>>
    fun getCurrentUser() : Observable<User>
    fun getTopTracks(param: TopParam) : Observable<TopTracksResponse>
    fun getTopArtists(param: TopParam) : Observable<TopArtistsResponse>
    fun createPlaylist(userId: Int, playlistName : String) : Observable<PlaylistResponse>
}