package com.jvmori.topify.data

import com.jvmori.topify.data.network.NetworkDataSource
import com.jvmori.topify.data.response.Artists
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(
    private val networkDataSource: NetworkDataSource
){
    fun searchArtist(query: String) : Observable<List<Artists>>{
        return networkDataSource.searchArtists(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}