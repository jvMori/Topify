package com.jvmori.topify.data.response.search

import com.jvmori.topify.data.response.top.Album

data class ArtistsAlbumsResponse (
    val href : String,
    val items : List<Album>
)