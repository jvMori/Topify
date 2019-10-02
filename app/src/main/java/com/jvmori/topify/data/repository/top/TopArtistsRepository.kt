package com.jvmori.topify.data.repository.top

import com.jvmori.topify.data.db.dao.TopArtistsDao
import com.jvmori.topify.data.db.entity.TopArtistsResponse
import com.jvmori.topify.data.network.NetworkDataSource
import com.jvmori.topify.data.repository.BaseRepository
import com.jvmori.topify.data.response.top.TopParam
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopArtistsRepository @Inject constructor(
    private var networkDataSource: NetworkDataSource,
    private var topArtistsDao : TopArtistsDao
    ) :
    BaseRepository<TopArtistsResponse, TopParam>,
    TopRepository<TopArtistsResponse>
{
    override fun getItemsLocal(params: TopParam): Maybe<TopArtistsResponse> {
        return topArtistsDao.getItems(params.timeRange, params.limit)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun getItemsRemote(params: TopParam): Maybe<TopArtistsResponse> {
        return networkDataSource.getTopArtists(params)
            .firstElement()
            .doOnSuccess {
                it.timeRange = params.timeRange
                it.timestamp = System.currentTimeMillis()
                it.countLimit = params.limit
                insert{
                    topArtistsDao.insert(it)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun isItemUpToDate(item: TopArtistsResponse): Boolean {
        return item.timestamp == 0L || System.currentTimeMillis() - item.timestamp < 3600000
    }

    override fun getTop(topParam: TopParam): Observable<TopArtistsResponse> {
        return getItems(topParam)
    }
}