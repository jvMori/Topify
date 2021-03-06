package com.jvmori.topify.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jvmori.topify.data.db.entity.AuthKey
import io.reactivex.Maybe

@Dao
interface AuthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(authKey: AuthKey)

    @Query("Select * from topify_key")
    fun getItems(): Maybe<AuthKey>
}