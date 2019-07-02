package com.jvmori.topify.data.response.top


data class TopArtistsResponse(
    val href: String,
    val artists: List<ArtistItem>
)