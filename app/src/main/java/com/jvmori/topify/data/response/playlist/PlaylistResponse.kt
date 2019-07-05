package com.jvmori.topify.data.response.playlist


import com.google.gson.annotations.SerializedName
import com.jvmori.topify.data.response.top.Image

data class PlaylistResponse(
    val `public`: Boolean,
    val collaborative: Boolean,
    val description: String,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val followers: Followers,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val owner: Owner,
    @SerializedName("primary_color")
    val primaryColor: Any,
    @SerializedName("snapshot_id")
    val snapshotId: String,
    val tracks: Tracks,
    val type: String,
    val uri: String
)