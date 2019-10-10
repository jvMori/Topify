package com.jvmori.topify.data.repository.top

import com.jvmori.topify.data.response.top.TopParam
import io.reactivex.Maybe
import io.reactivex.Observable

interface TopParamsRepository {
    fun getTop() : Maybe<TopParam>
    fun insert(topParam: TopParam)
}