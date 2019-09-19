package com.jvmori.topify.data.repository

import android.util.Log
import com.jvmori.topify.data.db.dao.TopTracksDao
import com.jvmori.topify.data.network.NetworkDataSource
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.data.db.entity.TopTracksResponse
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopTracksRepository @Inject constructor(
    private var networkDataSource: NetworkDataSource,
    private var topTracksDao: TopTracksDao
) : BaseRepository<TopTracksResponse, TopParam> {

    override fun getItemsRemote(params: TopParam): Maybe<TopTracksResponse> {
        return networkDataSource.getTopTracks(params)
            .firstElement()
            .doOnSuccess {
                it.timestamp = System.currentTimeMillis()
                it.timeRange = params.timeRange
                it.countLimit = params.limit
               // insert(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun getItemsLocal(params: TopParam): Maybe<TopTracksResponse> {
        return topTracksDao.getItems(params.timeRange, params.limit)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                Log.i("TOPIFY", it.toString())
            }
            .doOnComplete {
                Log.i("TOPIFY", "No data in db!")
            }
    }

    private fun insert(data : TopTracksResponse){
        Completable.fromAction {
            topTracksDao.insert(data)
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun isItemUpToDate(item: TopTracksResponse): Boolean {
        return item.timestamp == 0L || System.currentTimeMillis() - item.timestamp < 3600000
    }

}