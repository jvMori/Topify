package com.jvmori.topify.data.response.user


import com.google.gson.annotations.SerializedName
import com.jvmori.topify.data.response.top.ExternalUrls
import com.jvmori.topify.data.response.top.Followers
import com.jvmori.topify.data.response.top.Image

data class User(
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val followers: Followers,
    val href: String,
    val id: String,
    val images: List<Image>,
    val type: String,
    val uri: String
)