package com.jvmori.topify.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface SpotifyApi {
    @GET
    fun authorize(@Url authorizeUrl : String, @QueryMap parameters : Map<String, String>) : Single<String>
}