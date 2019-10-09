package com.jvmori.topify.data.response.top

data class TopParam (
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