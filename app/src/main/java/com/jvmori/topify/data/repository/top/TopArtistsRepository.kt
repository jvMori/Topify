package com.jvmori.topify.data.repository.top

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
    private var networkDataSource: NetworkDataSource
    ) :
    BaseRepository<TopArtistsResponse, TopParam>,
    TopRepository<TopArtistsResponse>
{
    override fun getItemsLocal(params: TopParam): Maybe<TopArtistsResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemsRemote(params: TopParam): Maybe<TopArtistsResponse> {
        return networkDataSource.getTopArtists(params)
            .firstElement()
            .doOnSuccess {

            }
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
    }

    override fun isItemUpToDate(item: TopArtistsResponse): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTop(topParam: TopParam): Observable<TopArtistsResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}