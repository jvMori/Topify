package com.jvmori.topify.data.repository

import com.jvmori.topify.data.db.BaseDao
import io.reactivex.Maybe

interface AuthRepository : BaseRepository<String>{
    
    override var baseDao: BaseDao<String>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun getItemsRemote(): Maybe<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isItemUpToDate(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}