package com.jvmori.topify.data.response.top


import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrlsXX,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)