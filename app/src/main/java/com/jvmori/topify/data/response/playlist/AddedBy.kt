package com.jvmori.topify.data.response.playlist


import com.google.gson.annotations.SerializedName
import com.jvmori.topify.data.response.top.ExternalUrls

data class AddedBy(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val href: String,
    val id: String,
    val type: String,
    val uri: String
)