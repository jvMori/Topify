package com.jvmori.topify.data.response.playlist


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.jvmori.topify.data.response.top.ExternalUrls
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val href: String,
    val id: String,
    val type: String,
    val uri: String
) : Parcelable