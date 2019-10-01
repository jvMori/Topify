package com.jvmori.topify.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jvmori.topify.data.db.entity.TopArtistsResponse
import io.reactivex.Maybe

@Dao
interface TopArtistsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items : TopArtistsResponse)

    @Query("Select * from topify_top_artists where timeRange like:timesRange AND countLimit like:count")
    fun getItems(timesRange: String, count : Int): Maybe<TopArtistsResponse>
}