package com.jvmori.topify.data

import com.jvmori.topify.data.response.search.Artists
import com.jvmori.topify.data.response.user.User
import io.reactivex.Observable

interface IRepository {
    fun searchArtist(query: String) : Observable<List<Artists>>
    fun getCurrentUser() : Observable<User>
}