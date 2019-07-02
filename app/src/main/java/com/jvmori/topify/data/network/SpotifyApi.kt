package com.jvmori.topify.data.network

import com.jvmori.topify.data.response.search.Artists
import com.jvmori.topify.data.response.user.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface SpotifyApi {
    @GET("search")
    fun search(@Query("query") query : String, @Query("type") type : String) : Observable<List<Artists>>

    @GET("me")
    fun getUser() : Observable<User>

    @GET("me/top/{type}")
    fun getTop(@Path("type") type: String)
}