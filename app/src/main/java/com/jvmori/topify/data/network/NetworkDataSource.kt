package com.jvmori.topify.data.network

import com.jvmori.topify.data.response.Artists
import io.reactivex.Observable

interface NetworkDataSource {
    fun searchArtists(query: String) : Observable<List<Artists>>
}