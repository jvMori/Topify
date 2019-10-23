package com.jvmori.topify.data.response.search

import com.jvmori.topify.data.response.top.ArtistItem

data class Artists(
    val href: String,
    val items: List<ArtistItem>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: String,
    val total: Int
)