package com.jvmori.topify.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jvmori.topify.data.response.top.Track

@Entity(tableName = "topify_top_tracks", primaryKeys = ["timeRange", "countLimit"])
data class TopTracksResponse(
    var timeRange: String,
    var countLimit : Int,
    val href: String,
    @SerializedName("items")
    val tracks: List<Track>,
    var timestamp : Long
)