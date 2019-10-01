package com.jvmori.topify.data.repository.top

import com.jvmori.topify.data.response.top.TopParam
import io.reactivex.Observable

interface TopRepository<T> {
    fun getTop(topParam: TopParam) : Observable<T>
}