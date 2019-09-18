package com.jvmori.topify.data.db

import androidx.room.Dao
import io.reactivex.Maybe

@Dao
interface BaseDao<T>
{
    fun getItems() : Maybe<T>
}