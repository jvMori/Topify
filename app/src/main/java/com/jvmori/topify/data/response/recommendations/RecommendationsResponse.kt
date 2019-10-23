package com.jvmori.topify.data.response.recommendations


import com.jvmori.topify.data.response.top.Track

data class RecommendationsResponse(
    var seeds: List<Seed>,
    var tracks: List<Track>
)