package com.jvmori.topify.data.response.search

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val followers: Followers,
    val genres: List<Any>,
    val href: String,
    val id: String,
    val images: List<Any>,
    val name: String,
    val popularity: Int,
    val type: String,
    val uri: String
)