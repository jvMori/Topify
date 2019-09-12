package com.jvmori.topify.data.network

import com.jvmori.topify.data.response.playlist.AddTracks
import com.jvmori.topify.data.response.playlist.AddTracksResponse
import com.jvmori.topify.data.response.playlist.NewPlaylist
import com.jvmori.topify.data.response.playlist.PlaylistResponse
import com.jvmori.topify.data.response.search.Artists
import com.jvmori.topify.data.response.top.TopArtistsResponse
import com.jvmori.topify.data.response.top.TopTracksResponse
import com.jvmori.topify.data.response.user.User
import io.reactivex.Observable
import retrofit2.http.*


interface SpotifyApi {
    @GET("search")
    fun search(@Query("query") query: String, @Query("type") type: String): Observable<List<Artists>>

    @GET("me")
    fun getUser(): Observable<User>

    @GET("me/top/artists")
    fun getTopArtists(@QueryMap parameters: Map<String, String>): Observable<TopArtistsResponse>

    @GET("me/top/tracks")
    fun getTopTracks(@QueryMap parameters: Map<String, String>): Observable<TopTracksResponse>

    @POST("users/{user_id}/playlists")
    @Headers("Content-Type: application/json")
    fun createPlaylist(
        @Path("user_id") userId: String,
        @Body playlist: NewPlaylist
    ): Observable<PlaylistResponse>

    @POST("playlists/{playlist_id}/tracks")
    @Headers("Content-Type: application/json")
    fun addTracksToPlaylist(
        @Path("playlist_id") playlistId: String,
        @Body tracks: AddTracks
    ): Observable<AddTracksResponse>
}