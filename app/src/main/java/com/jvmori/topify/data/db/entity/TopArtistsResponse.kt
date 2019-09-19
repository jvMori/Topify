package com.jvmori.topify.data.db.entity

import com.google.gson.annotations.SerializedName
import com.jvmori.topify.data.response.top.ArtistItem


data class TopArtistsResponse(
    val href: String,
    @SerializedName("items")
    val artists: List<ArtistItem>
)