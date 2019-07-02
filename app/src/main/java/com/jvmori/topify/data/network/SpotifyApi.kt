package com.jvmori.topify.data.network

import com.jvmori.topify.data.response.search.Artists
import com.jvmori.topify.data.response.top.TopArtistsResponse
import com.jvmori.topify.data.response.top.TopTracksResponse
import com.jvmori.topify.data.response.user.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface SpotifyApi {
    @GET("search")
    fun search(@Query("query") query : String, @Query("type") type : String) : Observable<List<Artists>>

    @GET("me")
    fun getUser() : Observable<User>

    @GET("me/top/artists")
    fun getTopArtists(@QueryMap parameters : Map<String, String>) : Observable<TopArtistsResponse>

    @GET("me/top/tracks")
    fun getTopTracks(@QueryMap parameters : Map<String, String>) : Observable<TopTracksResponse>
}