package com.jvmori.topify.data.response.top

import com.google.gson.annotations.SerializedName


data class TopTracksResponse(
    val href: String,
    @SerializedName("items")
    val tracks: List<Track>
)