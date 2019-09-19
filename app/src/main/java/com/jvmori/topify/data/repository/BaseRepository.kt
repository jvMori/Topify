package com.jvmori.topify.data.repository

import io.reactivex.Maybe
import io.reactivex.Observable

interface BaseRepository<T, K> {

    fun getItems(params : K) : Observable<T>{
        return Maybe.concat(getItemsLocal(params), getItemsRemote(params))
            .filter {
                isItemUpToDate(it)
            }
            .take(1)
            .toObservable()
    }

    fun getItemsLocal(params: K) : Maybe<T>
    fun getItemsRemote(params : K) : Maybe<T>
    fun isItemUpToDate(item : T) : Boolean
}