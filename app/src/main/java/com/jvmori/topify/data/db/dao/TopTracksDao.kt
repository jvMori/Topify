package com.jvmori.topify.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jvmori.topify.data.db.entity.TopTracksResponse
import com.jvmori.topify.data.response.top.TopParam
import io.reactivex.Maybe

@Dao
interface TopTracksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items : TopTracksResponse)

    @Query("Select * from topify_top_tracks where timeRange like:timesRange AND `limit` like:count")
    fun getItems(timesRange: String, count : Int): Maybe<TopTracksResponse>
}