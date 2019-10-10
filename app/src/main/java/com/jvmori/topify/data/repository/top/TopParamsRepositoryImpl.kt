package com.jvmori.topify.data.repository.top

import com.jvmori.topify.data.db.dao.TopTracksDao
import com.jvmori.topify.data.response.top.TopParam
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopParamsRepositoryImpl @Inject constructor(
    private var topTracksDao: TopTracksDao
) : TopParamsRepository {

    override fun insert(topParam: TopParam) {
        Completable.fromAction {
           topTracksDao.insertTopParams(topParam)
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun getTop(): Maybe<TopParam> {
        return topTracksDao.getTopParams()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}