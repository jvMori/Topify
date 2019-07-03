package com.jvmori.topify.data.response.top

data class TopParam (
    val limit : Int = 50,
    val timeRange : String = TimeRange().mediumTerm
)
data class TimeRange(
    val longTerm : String = "long_term",
    val mediumTerm : String = "medium_term",
    val shortTerm : String = "short_term"
)