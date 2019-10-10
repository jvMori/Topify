package com.jvmori.topify.data.response.top

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topify_top_params")
data class TopParam (
    @PrimaryKey
    val index: Int = 0,
    var limit : Int = 50,
    var timeRange : String = TimeRange().mediumTerm,
    var topCategory : TopCategory
)
data class TimeRange(
    val longTerm : String = "long_term",
    val mediumTerm : String = "medium_term",
    val shortTerm : String = "short_term"
)
enum class TopCategory {
    TRACKS, ARTISTS
}