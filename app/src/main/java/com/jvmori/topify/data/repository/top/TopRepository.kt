package com.jvmori.topify.data.repository.top

import com.jvmori.topify.data.response.top.TopParam
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface TopRepository<T> {
    fun getTop(topParam: TopParam) : Observable<T>

    fun insert(insert : () -> Unit) {
        Completable.fromAction {
            insert()
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}