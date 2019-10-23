package com.jvmori.topify.data.network

import com.jvmori.topify.data.db.entity.TopArtistsResponse
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.data.db.entity.TopTracksResponse
import com.jvmori.topify.data.response.playlist.*
import com.jvmori.topify.data.response.search.Artists
import com.jvmori.topify.data.response.top.Image
import com.jvmori.topify.data.response.user.User
import io.reactivex.Observable
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    val api: SpotifyApi
) : NetworkDataSource {

    override fun searchArtists(query: String): Observable<Artists> {
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

    override fun createPlaylist(userId: String, playlistName: String, playlistDescription : String): Observable<PlaylistResponse> {
        return api.createPlaylist(userId, NewPlaylist(name = playlistName, description = playlistDescription))
    }

    override fun addTracksToPlaylist(playlistId: String, tracks: AddTracks): Observable<AddTracksResponse> {
        return api.addTracksToPlaylist(playlistId, tracks)
    }

    override fun getPlaylistCoverImage(playlistId: String): Observable<List<Image>> {
        return api.getPlaylistCoverImage(playlistId)
    }

    private fun createMap(param: TopParam): HashMap<String, String> {
        val map = hashMapOf<String, String>()
        map["limit"] = param.limit.toString()
        map["time_range"] = param.timeRange
        return map
    }

}