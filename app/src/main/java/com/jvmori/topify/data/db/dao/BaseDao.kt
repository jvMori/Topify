package com.jvmori.topify.data.db.dao

import androidx.room.Dao
import io.reactivex.Maybe

@Dao
interface BaseDao<T, K>
{
    fun getItems(params : K) : Maybe<T>
}