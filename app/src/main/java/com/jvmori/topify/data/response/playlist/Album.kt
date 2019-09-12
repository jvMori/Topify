package com.jvmori.topify.data.response.playlist


import com.google.gson.annotations.SerializedName
import com.jvmori.topify.data.response.top.Image

data class Album(
    @SerializedName("album_type")
    val albumType: String,
    val artists: List<Artist>,
    @SerializedName("available_markets")
    val availableMarkets: List<String>,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("release_date_precision")
    val releaseDatePrecision: String,
    @SerializedName("total_tracks")
    val totalTracks: Int,
    val type: String,
    val uri: String
)