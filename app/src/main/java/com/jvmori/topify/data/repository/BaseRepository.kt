package com.jvmori.topify.data.repository

import android.util.Log
import com.jvmori.topify.data.db.BaseDao
import io.reactivex.Maybe
import io.reactivex.Observable

interface BaseRepository<T> {

    var baseDao : BaseDao<T>

    fun getItems() : Observable<T>{
        return Maybe.concat(getItemsLocal(), getItemsRemote())
            .filter {
                isItemUpToDate(it)
            }
            .take(1)
            .toObservable()
    }

    fun getItemsLocal() : Maybe<T>{
        return baseDao.getItems()
            .doOnSuccess {
                Log.i("TOPIFY", it.toString())
            }
            .doOnComplete {
                Log.i("TOPIFY", "No data in db!")
            }
    }

    fun getItemsRemote() : Maybe<T>
    fun isItemUpToDate(item : T) : Boolean
}