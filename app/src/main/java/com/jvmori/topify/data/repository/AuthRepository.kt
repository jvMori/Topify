package com.jvmori.topify.data.repository

import com.jvmori.topify.data.db.dao.AuthDao
import com.jvmori.topify.data.db.entity.AuthKey
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private  var authDao: AuthDao
) : BaseRepository<AuthKey, String> {

    fun insert(key : String){
        Completable.fromAction {
            authDao.insert(AuthKey(key=key, timestamp =  System.currentTimeMillis()))
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun getItemsLocal(params: String): Maybe<AuthKey> {
        return authDao.getItems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun getItemsRemote(params : String): Maybe<AuthKey> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isItemUpToDate(item : AuthKey): Boolean {
        return false
    }
}