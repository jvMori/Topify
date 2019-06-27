package com.jvmori.topify.data.network

import com.jvmori.topify.data.response.Artists
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface SpotifyApi {
    @GET("search")
    fun search(@Query("q") query : String, @Query("type") type : String) : Observable<List<Artists>>
}