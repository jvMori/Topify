package com.jvmori.topify.data.repository.top

import com.jvmori.topify.data.db.dao.TopTracksDao
import com.jvmori.topify.data.response.top.TopParam
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopParamsRepository @Inject constructor(
    private var topTracksDao: TopTracksDao
) : TopRepository<TopParam> {

    override fun getTop(topParam: TopParam): Observable<TopParam> {
       return topTracksDao.getTopParams()
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeOn(Schedulers.io())
           .toObservable()
    }

    fun insertTopParams(topParam: TopParam){
        insert { topTracksDao.insertTopParams(topParam) }
    }
}