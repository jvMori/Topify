package com.jvmori.topify.data.response.top

import com.google.gson.annotations.SerializedName


data class TopArtistsResponse(
    val href: String,
    @SerializedName("items")
    val artists: List<ArtistItem>
)