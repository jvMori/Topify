package com.jvmori.topify.data.network

import com.jvmori.topify.data.response.Artists
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface SpotifyApi {
    @GET("/search&type=track,artist")
    fun search(@Query("q") query : String) : Observable<List<Artists>>
}