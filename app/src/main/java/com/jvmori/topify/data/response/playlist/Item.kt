package com.jvmori.topify.data.response.playlist


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("added_at")
    val addedAt: String,
    @SerializedName("added_by")
    val addedBy: AddedBy,
    @SerializedName("is_local")
    val isLocal: Boolean,
    @SerializedName("primary_color")
    val primaryColor: Any,
    val track: Track,
    @SerializedName("video_thumbnail")
    val videoThumbnail: VideoThumbnail
)