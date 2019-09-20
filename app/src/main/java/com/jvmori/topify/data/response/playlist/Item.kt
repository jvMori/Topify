package com.jvmori.topify.data.response.playlist


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.jvmori.topify.data.response.top.Track
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    @SerializedName("added_at")
    val addedAt: String,
    @SerializedName("is_local")
    val isLocal: Boolean,
    val track: Track
) : Parcelable