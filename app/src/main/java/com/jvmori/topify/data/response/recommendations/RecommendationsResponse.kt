package com.jvmori.topify.data.response.recommendations


import com.jvmori.topify.data.response.top.Track

data class RecommendationsResponse(
    var tracks: List<Track>,
    var seeds: List<Seed>

)