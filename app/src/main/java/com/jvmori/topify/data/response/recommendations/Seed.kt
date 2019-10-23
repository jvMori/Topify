package com.jvmori.topify.data.response.recommendations


data class Seed(
    var afterFilteringSize: Int,
    var afterRelinkingSize: Int,
    var href: String?,
    var id: String,
    var initialPoolSize: Int,
    var type: String
)