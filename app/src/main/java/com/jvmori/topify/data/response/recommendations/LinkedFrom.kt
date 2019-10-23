package com.jvmori.topify.data.response.recommendations


import com.google.gson.annotations.SerializedName

data class LinkedFrom(
    @SerializedName("external_urls")
    var externalUrls: ExternalUrls,
    var href: String,
    var id: String,
    var type: String,
    var uri: String
)