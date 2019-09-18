package com.jvmori.topify.data.repository

import com.jvmori.topify.data.db.BaseDao
import com.jvmori.topify.data.db.dao.AuthDao
import com.jvmori.topify.data.db.entity.AuthKey
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private  var authDao: AuthDao
) : BaseRepository<AuthKey> {

    override var baseDao: BaseDao<AuthKey>
        get() = authDao
        set(value) {}

    fun insert(key : String){
        Completable.fromAction {
            authDao.insert(AuthKey(key=key, timestamp =  System.currentTimeMillis()))
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun getItemsRemote(): Maybe<AuthKey> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isItemUpToDate(item : AuthKey): Boolean {
        return false
    }
}