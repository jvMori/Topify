package com.jvmori.topify.data.network

import com.jvmori.topify.data.db.entity.TopArtistsResponse
import com.jvmori.topify.data.db.entity.TopTracksResponse
import com.jvmori.topify.data.response.playlist.AddTracks
import com.jvmori.topify.data.response.playlist.AddTracksResponse
import com.jvmori.topify.data.response.playlist.NewPlaylist
import com.jvmori.topify.data.response.playlist.PlaylistResponse
import com.jvmori.topify.data.response.recommendations.RecommendationsResponse
import com.jvmori.topify.data.response.search.ArtistsAlbumsResponse
import com.jvmori.topify.data.response.search.ArtistsResponse
import com.jvmori.topify.data.response.top.Image
import com.jvmori.topify.data.response.user.User
import io.reactivex.Observable
import retrofit2.http.*


interface SpotifyApi {
    @GET("search")
    fun search(@Query("query") query: String, @Query("type") type: String):Observable<ArtistsResponse>

    @GET("me")
    fun getUser(): Observable<User>

    @GET("me/top/artists")
    fun getTopArtists(@QueryMap parameters: Map<String, String>): Observable<TopArtistsResponse>

    @GET("me/top/tracks")
    fun getTopTracks(@QueryMap parameters: Map<String, String>): Observable<TopTracksResponse>

    @GET("playlists/{playlist_id}/images")
    fun getPlaylistCoverImage(@Path ("playlist_id") playlistId : String) : Observable<List<Image>>

    @GET("recommendations")
    fun getRecommendations(@QueryMap parameters: Map<String, String>) : Observable<RecommendationsResponse>

    @GET("artists/{id}/albums")
    fun getArtistsAlbums(@Path("id") artistId : String) : Observable<ArtistsAlbumsResponse>

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